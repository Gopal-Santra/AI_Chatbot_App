package com.example.testapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.noties.markwon.Markwon

class ChatAdapter (private val  chatList: MutableList<ChatModel>,private val context: GoogleAIActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val USER = 0
        const val AI = 1

    }

    override fun getItemViewType(position: Int): Int {

        return if(chatList[position].isUser){
            USER
        }
        else{
            AI
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if(viewType == USER){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_user,parent,false)
            UserViewHolder(view)

        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_ai,parent,false)
            AiViewHolder(view)

        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val chat = chatList[position]
        if(holder is UserViewHolder){

            val markwon = Markwon.create(context)
            markwon.setMarkdown(holder.tvMessage,chat.message)
        }
        else if(holder is AiViewHolder){
            val markwon = Markwon.create(context)
            markwon.setMarkdown(holder.tvMessage,chat.message)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvMessage : TextView = itemView.findViewById(R.id.tvMessage)
    }

    class AiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvMessage : TextView = itemView.findViewById(R.id.tvMessage)
    }
}

