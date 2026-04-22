import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import patchService from '../services/patchService'

export default function PatchListPage() {
  const [records, setRecords] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const navigate = useNavigate()

  

  useEffect(() => {
  const load = async () => {
    setLoading(true)
    try {
      const res = await patchService.getAll()
      setRecords(res.data.content ?? res.data)
    } catch {
      setError('Failed to load records.')
    } finally {
      setLoading(false)
    }
  }
  load()
}, [])

  if (loading) {
    return (
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '200px' }}>
        <p style={{ color: '#6b7280' }}>Loading patch records…</p>
      </div>
    )
  }

  if (error) {
    return (
      <div style={{ textAlign: 'center', marginTop: '80px' }}>
        <p style={{ color: 'red' }}>{error}</p>
      </div>
    )
  }

  if (records.length === 0) {
    return (
      <div style={{ textAlign: 'center', marginTop: '80px' }}>
        <p style={{ fontSize: '20px', fontWeight: '600', color: '#374151' }}>No patch records yet</p>
        <p style={{ color: '#9ca3af', marginTop: '8px' }}>Create your first record to get started.</p>
      </div>
    )
  }

  return (
    <div style={{ maxWidth: '1100px', margin: '0 auto', padding: '32px 16px' }}>
      <h1 style={{ fontSize: '22px', fontWeight: 'bold', marginBottom: '24px' }}>
        Patch Compliance Records
      </h1>
      <table style={{ width: '100%', borderCollapse: 'collapse', background: 'white', borderRadius: '8px', boxShadow: '0 1px 4px rgba(0,0,0,0.08)' }}>
        <thead style={{ background: '#f9fafb' }}>
          <tr>
            <th style={{ padding: '12px 16px', textAlign: 'left', fontSize: '12px', fontWeight: '600', color: '#6b7280', textTransform: 'uppercase' }}>Asset</th>
            <th style={{ padding: '12px 16px', textAlign: 'left', fontSize: '12px', fontWeight: '600', color: '#6b7280', textTransform: 'uppercase' }}>Patch ID</th>
            <th style={{ padding: '12px 16px', textAlign: 'left', fontSize: '12px', fontWeight: '600', color: '#6b7280', textTransform: 'uppercase' }}>Severity</th>
            <th style={{ padding: '12px 16px', textAlign: 'left', fontSize: '12px', fontWeight: '600', color: '#6b7280', textTransform: 'uppercase' }}>Status</th>
            <th style={{ padding: '12px 16px', textAlign: 'left', fontSize: '12px', fontWeight: '600', color: '#6b7280', textTransform: 'uppercase' }}>Deadline</th>
            <th style={{ padding: '12px 16px' }}></th>
          </tr>
        </thead>
        <tbody>
          {records.map(r => (
            <tr key={r.id} style={{ borderTop: '1px solid #f3f4f6' }}>
              <td style={{ padding: '12px 16px', fontWeight: '500' }}>{r.assetName}</td>
              <td style={{ padding: '12px 16px', color: '#6b7280', fontFamily: 'monospace' }}>{r.patchId}</td>
              <td style={{ padding: '12px 16px' }}>{r.severity}</td>
              <td style={{ padding: '12px 16px' }}>{r.status}</td>
              <td style={{ padding: '12px 16px', color: '#6b7280' }}>{r.patchDeadline ?? '—'}</td>
              <td style={{ padding: '12px 16px' }}>
                <button
                  onClick={() => navigate(`/patch/${r.id}`)}
                  style={{ color: '#1B4F8A', background: 'none', border: 'none', cursor: 'pointer', fontSize: '13px' }}
                >
                  View →
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}