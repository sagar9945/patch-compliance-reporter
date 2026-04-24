import { useParams, useNavigate } from 'react-router-dom'

export default function PatchDetailPage() {
  const { id } = useParams()
  const navigate = useNavigate()

  return (
    <div style={{ maxWidth: '700px', margin: '0 auto', padding: '32px 16px' }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: '12px', marginBottom: '24px' }}>
        <button
          onClick={() => navigate(-1)}
          style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#1B4F8A', fontSize: '14px', padding: 0 }}
        >
          ← Back
        </button>
        <h1 style={{ fontSize: '22px', fontWeight: 'bold', color: '#111827', margin: 0 }}>
          Patch Record #{id}
        </h1>
        <button
          onClick={() => navigate(`/patch/${id}/edit`)}
          style={{ marginLeft: 'auto', backgroundColor: '#1B4F8A', color: 'white', padding: '7px 18px', borderRadius: '6px', border: 'none', cursor: 'pointer', fontSize: '13px', fontWeight: '600' }}
        >
          ✏️ Edit
        </button>
      </div>
      <div style={{ background: 'white', borderRadius: '10px', padding: '28px', boxShadow: '0 1px 6px rgba(0,0,0,0.08)' }}>
        <p style={{ color: '#9ca3af' }}>Full detail view coming Day 6. Edit button above works now.</p>
      </div>
    </div>
  )
}