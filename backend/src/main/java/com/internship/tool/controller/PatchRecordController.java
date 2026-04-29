package com.internship.tool.controller;

import com.internship.tool.service.PatchRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/patch-records")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Patch Records", description = "CRUD operations for patch compliance records")
public class PatchRecordController {

    private final PatchRecordService patchRecordService;

    // ── GET /api/patch-records ────────────────────────
    @GetMapping
    @Operation(summary = "Get all patch records",
               description = "Returns paginated list of all patch compliance records")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Records retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — JWT token required")
    })
    public ResponseEntity<Page<?>> getAll(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size")             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(patchRecordService.getAll(PageRequest.of(page, size)));
    }

    // ── GET /api/patch-records/{id} ───────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Get patch record by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Record found"),
        @ApiResponse(responseCode = "404", description = "Record not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<?> getById(
            @Parameter(description = "Record ID") @PathVariable Long id) {
        return ResponseEntity.ok(patchRecordService.getById(id));
    }

    // ── POST /api/patch-records ───────────────────────
    @PostMapping
    @Operation(summary = "Create a new patch record")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Record created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed — check required fields"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
        public ResponseEntity<?> create(
            @RequestBody Object request) {       
             return ResponseEntity.status(HttpStatus.CREATED)
                .body(patchRecordService.create(request));
    }

    // ── PUT /api/patch-records/{id} ───────────────────
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patch record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Record updated successfully"),
        @ApiResponse(responseCode = "404", description = "Record not found"),
        @ApiResponse(responseCode = "400", description = "Validation failed"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<?> update(
            @Parameter(description = "Record ID") @PathVariable Long id,
            @Valid @RequestBody Object request) {
        return ResponseEntity.ok(patchRecordService.update(id, request));
    }

    // ── DELETE /api/patch-records/{id} ────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete a patch record",
               description = "Sets record status to EXEMPT — does not permanently delete")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Record not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Record ID") @PathVariable Long id) {
        patchRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ── GET /api/patch-records/search ─────────────────
    @GetMapping("/search")
    @Operation(summary = "Search patch records",
               description = "Search by asset name or patch ID")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    public ResponseEntity<?> search(
            @Parameter(description = "Search term") @RequestParam String q) {
        return ResponseEntity.ok(patchRecordService.search(q));
    }

    // ── GET /api/patch-records/stats ──────────────────
    @GetMapping("/stats")
    @Operation(summary = "Get dashboard statistics",
               description = "Returns total, compliant, non-compliant counts and average score")
    @ApiResponse(responseCode = "200", description = "Stats returned successfully")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(patchRecordService.getStats());
    }

    // ── GET /api/patch-records/export ─────────────────
    @GetMapping("/export")
    @Operation(summary = "Export all records as CSV",
               description = "Downloads all patch records as a CSV file")
    @ApiResponse(responseCode = "200", description = "CSV file downloaded successfully")
    public ResponseEntity<byte[]> exportCsv() {
        byte[] csvBytes = patchRecordService.exportToCsv();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"patch-records.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvBytes);
    }

    // ── POST /api/patch-records/upload ────────────────
    @PostMapping("/upload")
    @Operation(summary = "Upload a file attachment for a patch record",
               description = "Accepts PDF, PNG, JPG only. Max size 5MB.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "File uploaded successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid file type or size exceeded"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Map<String, String>> upload(
            @Parameter(description = "File to upload (PDF/PNG/JPG, max 5MB)")
            @RequestParam("file") MultipartFile file) {

        // ── Validation ────────────────────────────────
        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "File is empty"));
        }

        // Size validation — max 5MB
        long maxSize = 5 * 1024 * 1024L;
        if (file.getSize() > maxSize) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "File size exceeds 5MB limit. Actual size: "
                            + (file.getSize() / 1024 / 1024) + "MB"));
        }

        // Type validation — only PDF, PNG, JPG
        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.equals("application/pdf") &&
                 !contentType.equals("image/png") &&
                 !contentType.equals("image/jpeg"))) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error",
                            "Invalid file type: " + contentType +
                            ". Allowed types: PDF, PNG, JPG"));
        }

        // ── Save file ─────────────────────────────────
        String savedName = patchRecordService.saveFile(file);

        return ResponseEntity.ok(Map.of(
                "message",  "File uploaded successfully",
                "filename", savedName,
                "size",     file.getSize() + " bytes",
                "type",     contentType
        ));
    }
}