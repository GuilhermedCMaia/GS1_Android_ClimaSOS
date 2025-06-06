package br.com.fiap.gs1_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventoAdapter(
    private val eventos: List<Evento>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLocal: TextView = itemView.findViewById(R.id.textViewLocal)
        val textViewTipoEvento: TextView = itemView.findViewById(R.id.textViewTipoEvento)
        val textViewGrauImpacto: TextView = itemView.findViewById(R.id.textViewGrauImpacto)
        val textViewData: TextView = itemView.findViewById(R.id.textViewData)
        val textViewPessoasAfetadas: TextView = itemView.findViewById(R.id.textViewPessoasAfetadas)
        val buttonExcluir: Button = itemView.findViewById(R.id.buttonExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        
        holder.textViewLocal.text = "Local: ${evento.local}"
        holder.textViewTipoEvento.text = "Tipo: ${evento.tipoEvento}"
        holder.textViewGrauImpacto.text = "Impacto: ${evento.grauImpacto}"
        holder.textViewData.text = "Data: ${evento.data}"
        holder.textViewPessoasAfetadas.text = "Pessoas afetadas: ${evento.pessoasAfetadas}"
        
        holder.buttonExcluir.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = eventos.size
}