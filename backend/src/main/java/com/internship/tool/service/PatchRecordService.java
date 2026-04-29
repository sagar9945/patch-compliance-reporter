package com.internship.tool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j

public class PatchRecordService {

    // ── Get all ───────────────────────────────────────
    public Page<?> getAll(Pageable pageable) {
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    // ── Get by ID ─────────────────────────────────────
    public Object getById(Long id) {
        throw new RuntimeException("Record not found: " + id);
    }

    // ── Create ────────────────────────────────────────
    public Object create(Object request) {
        return request;
    }

    // ── Update ────────────────────────────────────────
    public Object update(Long id, Object request) {
        log.info("Updating record: {}", id);
        return request;
    }

    // ── Delete (soft) ─────────────────────────────────
    public void delete(Long id) {
        log.info("Soft deleting record: {}", id);
    }

    // ── Search ────────────────────────────────────────
    public List<?> search(String query) {
        log.info("Searching for: {}", query);
        return Collections.emptyList();
    }

    // ── Stats ─────────────────────────────────────────
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total",        0);
        stats.put("compliant",    0);
        stats.put("nonCompliant", 0);
        stats.put("pending",      0);
        stats.put("avgScore",     0.0);
        stats.put("bySeverity",   Collections.emptyList());
        return stats;
    }

    // ── Export CSV ────────────────────────────────────
    public byte[] exportToCsv() {
        try {
            ByteArrayOutputStream out    = new ByteArrayOutputStream();
            PrintWriter           writer = new PrintWriter(out);
            writer.println(
                "ID,Asset Name,Asset IP,Patch ID,Patch Title," +
                "Severity,Status,Compliance Score,Deadline,Created At"
            );
            writer.flush();
            return out.toByteArray();
        } catch (Exception e) {
            log.error("CSV export failed: {}", e.getMessage());
            return new byte[0];
        }
    }

    // ── Save uploaded file ────────────────────────────
    public String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String original  = Objects.requireNonNull(file.getOriginalFilename());
            String extension = original.substring(original.lastIndexOf('.'));
            String savedName = UUID.randomUUID() + extension;
            Files.write(uploadPath.resolve(savedName), file.getBytes());
            log.info("File saved: {}", savedName);
            return savedName;
        } catch (Exception e) {
            log.error("File save failed: {}", e.getMessage());
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        }
    }
}