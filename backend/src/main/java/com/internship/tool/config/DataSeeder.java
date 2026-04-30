package com.internship.tool.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSeeder {

    // ── Runs on startup (not in test profile) ─────────
    @Bean
    @Profile("!test")
    public CommandLineRunner seedData() {
        return args -> {
            log.info("DataSeeder: checking if seeding is needed...");
            log.info("DataSeeder: 15 demo records ready to seed when entity layer is wired.");
            log.info("DataSeeder: seed data defined below — connect to PatchRecordRepository to activate.");
        };
    }

    // ── 15 realistic demo patch records ───────────────
    // These will be saved once Java Dev 1 wires the entity + repository
    public static List<SeedRecord> getDemoRecords() {
        return List.of(
            new SeedRecord("web-server-01",   "192.168.1.10", "DevOps",       "CVE-2024-1234", "OpenSSL Critical Vulnerability Fix",       "CRITICAL", "NON_COMPLIANT", 25,  LocalDate.of(2024, 3, 1),  LocalDate.of(2024, 3, 15)),
            new SeedRecord("db-server-01",    "192.168.1.20", "DBA Team",     "CVE-2024-5678", "PostgreSQL Authentication Bypass",         "CRITICAL", "PENDING",       40,  LocalDate.of(2024, 3, 5),  LocalDate.of(2024, 3, 20)),
            new SeedRecord("app-server-01",   "192.168.1.30", "Backend Team", "KB5034441",     "Windows Server 2022 Security Update",      "HIGH",     "COMPLIANT",     90,  LocalDate.of(2024, 2, 1),  LocalDate.of(2024, 2, 28)),
            new SeedRecord("app-server-02",   "192.168.1.31", "Backend Team", "KB5034442",     "Windows Server 2019 Cumulative Update",    "HIGH",     "COMPLIANT",     85,  LocalDate.of(2024, 2, 1),  LocalDate.of(2024, 2, 28)),
            new SeedRecord("mail-server-01",  "192.168.1.40", "IT Team",      "CVE-2024-2345", "Exchange Server Remote Code Execution",    "CRITICAL", "NON_COMPLIANT", 10,  LocalDate.of(2024, 3, 10), LocalDate.of(2024, 3, 25)),
            new SeedRecord("file-server-01",  "192.168.1.50", "IT Team",      "CVE-2024-3456", "SMB Protocol Vulnerability",               "HIGH",     "COMPLIANT",     80,  LocalDate.of(2024, 1, 15), LocalDate.of(2024, 2, 15)),
            new SeedRecord("api-gateway-01",  "10.0.0.1",     "DevOps",       "CVE-2024-4567", "Nginx HTTP/2 Rapid Reset Attack",          "HIGH",     "COMPLIANT",     75,  LocalDate.of(2024, 2, 20), LocalDate.of(2024, 3, 5)),
            new SeedRecord("cache-server-01", "10.0.0.10",    "Backend Team", "CVE-2024-6789", "Redis Unauthenticated Access",             "MEDIUM",   "PENDING",       55,  LocalDate.of(2024, 3, 15), LocalDate.of(2024, 4, 1)),
            new SeedRecord("dev-server-01",   "192.168.2.10", "Dev Team",     "CVE-2024-7890", "Java Log4j Remote Code Execution",         "CRITICAL", "COMPLIANT",     95,  LocalDate.of(2024, 1, 1),  LocalDate.of(2024, 1, 31)),
            new SeedRecord("backup-server-01","192.168.1.60", "IT Team",      "CVE-2024-8901", "Backup Agent Privilege Escalation",        "MEDIUM",   "COMPLIANT",     70,  LocalDate.of(2024, 2, 10), LocalDate.of(2024, 3, 10)),
            new SeedRecord("vpn-server-01",   "10.0.1.1",     "Security",     "CVE-2024-9012", "VPN Client Buffer Overflow",               "HIGH",     "NON_COMPLIANT", 30,  LocalDate.of(2024, 3, 20), LocalDate.of(2024, 4, 5)),
            new SeedRecord("load-balancer-01","10.0.0.2",     "DevOps",       "CVE-2024-0123", "HAProxy HTTP Request Smuggling",           "MEDIUM",   "COMPLIANT",     88,  LocalDate.of(2024, 2, 25), LocalDate.of(2024, 3, 25)),
            new SeedRecord("monitor-server",  "192.168.1.70", "IT Team",      "CVE-2024-1357", "Prometheus Metrics Exposure",              "LOW",      "EXEMPT",        60,  LocalDate.of(2024, 3, 1),  LocalDate.of(2024, 4, 30)),
            new SeedRecord("ci-server-01",    "192.168.2.20", "Dev Team",     "CVE-2024-2468", "Jenkins Remote Code Execution",            "CRITICAL", "COMPLIANT",     92,  LocalDate.of(2024, 1, 20), LocalDate.of(2024, 2, 20)),
            new SeedRecord("proxy-server-01", "10.0.0.3",     "DevOps",       "CVE-2024-3579", "Squid Proxy Cache Poisoning",              "MEDIUM",   "PENDING",       45,  LocalDate.of(2024, 3, 25), LocalDate.of(2024, 4, 25))
        );
    }

    // ── Simple record holder ──────────────────────────
    public record SeedRecord(
        String assetName,
        String assetIp,
        String assetOwner,
        String patchId,
        String patchTitle,
        String severity,
        String status,
        int    complianceScore,
        LocalDate patchReleaseDate,
        LocalDate patchDeadline
    ) {}
}