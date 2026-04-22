import { useParams, useNavigate } from 'react-router-dom'

export default function PatchDetailPage() {
  const { id } = useParams()
  const navigate = useNavigate()

  return (
    <div className="max-w-2xl mx-auto px-4 py-10">
      <button onClick={() => navigate(-1)}
        className="text-sm mb-4 block" style={{color:'#1B4F8A'}}>
        ← Back to list
      </button>
      <h1 className="text-xl font-bold text-gray-800">Patch Record #{id}</h1>
      <p className="text-gray-400 mt-2">Detail view — coming Day 3.</p>
    </div>
  )
}