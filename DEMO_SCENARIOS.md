# Demo Scenarios — Java Developer 2 (Sagar M D)
# Tool-96 — Patch Compliance Reporter
# Demo Day: Friday 9 May 2026

---

## Pre-Demo Setup (Do Before Presenting)

1. Open browser → http://localhost:3000
2. Open VS Code terminal — backend running on port 8080
3. Browser zoom at 100%
4. Close all other browser tabs
5. DevTools closed

---

## SCENARIO 1 — Login + Records List + Search & Filter
**Time:** 0:00 – 1:30 | **Talking Points:** Authentication, Data Management

### Exact Steps:
1. Navigate to http://localhost:3000
   - **Expected:** Login page with shield icon and "Sign in to your account"

2. Type username: `sagar` | password: `password123` → click Sign In
   - **Expected:** Redirected to records list with 15 patch records

3. Point out the table columns
   - **Say:** "Each record shows Asset Name, Patch ID, Severity badge,
     Status badge, Compliance Score and Deadline"
   - **Expected:** CRITICAL badges in red, HIGH in orange, COMPLIANT in green

4. Type "CVE" in the search box
   - **Expected:** Table filters to show only CVE patch IDs — debounced
     (wait 400ms then filters automatically)

5. Select "Non-Compliant" from Status dropdown
   - **Expected:** Shows only NON_COMPLIANT records — 4 records

6. Click "✕ Clear" button
   - **Expected:** All 15 records shown again

7. Click the green "⬇ Export CSV" button
   - **Expected:** Downloads patch-records.csv file to computer

### Talking Points:
- "JWT authentication secures all endpoints"
- "Debounced search — waits 400ms to avoid API spam"
- "Status + date range filters work together"
- "One-click CSV export for compliance reports"

---

## SCENARIO 2 — Dashboard + Analytics
**Time:** 1:30 – 3:00 | **Talking Points:** Data Visualization, Business Insights

### Exact Steps:
1. Click "📊 Dashboard" in the navbar
   - **Expected:** Dashboard with 4 KPI cards:
     - Total Records: 15
     - Compliant: 7
     - Non-Compliant: 4
     - Avg Score: 64%

2. Point to the bar chart
   - **Say:** "Severity breakdown — 5 CRITICAL patches need immediate attention"
   - **Expected:** Red bar tallest for CRITICAL

3. Point to Status Breakdown section
   - **Say:** "7 compliant, 4 non-compliant, 3 pending"

4. Click "📈 View Full Analytics →" button
   - **Expected:** Analytics page loads

5. Point to 4 charts:
   - **Say:** "Bar chart — severity distribution"
   - **Say:** "Pie chart — status distribution"
   - **Say:** "Area chart — compliance trend over time"
   - **Say:** "Horizontal bar — score distribution"

6. Click period buttons: 1M → 3M → 6M → 1Y
   - **Expected:** Period selector highlights the selected button

7. Point to "Demo data" warning badge
   - **Say:** "When connected to live backend, this shows real-time data"

### Talking Points:
- "4 KPI cards give management instant visibility"
- "Recharts library — interactive, responsive charts"
- "Period selector lets you view trends over different timeframes"
- "Score distribution shows most assets score 81-100"

---

## SCENARIO 3 — Create Record + Detail Page + AI Panel
**Time:** 3:00 – 5:00 | **Talking Points:** CRUD, Validation, AI Features

### Exact Steps:
1. Click "+ New Record" button
   - **Expected:** Create form opens with 3 sections

2. Try submitting empty form — click "Create Record"
   - **Expected:** Red validation errors appear:
     - "Asset name is required"
     - "Patch ID is required"
     - "Patch title is required"
   - **Say:** "Frontend validation before API call"

3. Fill in the form:
   - Asset Name: `demo-server-01`
   - Asset IP: `192.168.1.99`
   - Patch ID: `CVE-2024-DEMO`
   - Patch Title: `Demo Security Patch`
   - Severity: `HIGH`
   - Status: `PENDING`
   - Score: `65`
   - Click "Create Record"
   - **Expected:** Redirected to records list with new record added

4. Click "View →" on web-server-01 (first record)
   - **Expected:** Detail page with:
     - Compliance Score badge (25% in red circle)
     - CRITICAL severity badge
     - NON_COMPLIANT status badge
     - Asset info and patch info cards

5. Point to Score Badge
   - **Say:** "Score of 25% — Poor — immediate action required
     shown in red circle"

6. Click "🔍 Describe" button in AI Analysis panel
   - **Expected:** Loading spinner "AI is analyzing this patch…"
   - After response: Blue card with AI description
   - **Say:** "Groq's LLaMA-3 model generates the description"

7. Click "💡 Recommend" button
   - **Expected:** Green card with AI recommendations
   - **Say:** "AI recommends specific actions with priority levels"

8. Click "✏️ Edit" button
   - **Expected:** Edit form opens pre-filled with existing data

9. Change Status to "COMPLIANT" → click "Save Changes"
   - **Expected:** Back to detail page, status updated

### Talking Points:
- "Complete CRUD — Create, Read, Update, Delete"
- "Soft delete — sets status to EXEMPT, never loses data"
- "AI panel calls Flask microservice → Groq API → LLaMA-3"
- "Responsive design — works on mobile, tablet, desktop"

---

## Quick Recovery Phrases (If Something Goes Wrong)

| Problem | What to Say |
|---|---|
| Backend not responding | "The AI service is processing — let me show the cached result" |
| Page takes long to load | "The system is seeding 15 demo records on first run" |
| Login fails | "Let me show the demo mode" — press F12, set token manually |
| Chart empty | "Charts populate from the database — here's the structure" |

---

## Key Numbers to Remember

- **15** demo records in the database
- **3** Flyway migrations (V1, V2, V3)
- **9** REST endpoints documented in Swagger
- **4** Recharts charts in Analytics
- **400ms** debounce delay on search
- **5MB** max file upload size
- **3** breakpoints: 375px / 768px / 1280px

---

## Technical Questions You Might Be Asked

**Q: How does JWT work in your app?**
A: "User logs in → Spring Boot generates JWT signed with secret key →
Frontend stores in localStorage → Sends as Bearer token in every request →
Spring Security validates on each API call"

**Q: What is Flyway?**
A: "Database migration tool — V1 creates tables, V2 adds audit trigger,
V3 adds performance indexes. Runs automatically on startup."

**Q: How does the AI work?**
A: "Frontend calls Spring Boot → Spring Boot calls Flask on port 5000 →
Flask sends prompt to Groq API → LLaMA-3-70b generates response →
Returns to frontend"

**Q: What is debouncing?**
A: "Instead of calling API on every keystroke, we wait 400ms after
user stops typing. Prevents unnecessary API calls."

**Q: What is soft delete?**
A: "We never permanently delete records — we set status to EXEMPT.
This preserves audit history."