package com.example.tictactoe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val Winning= listOf(listOf(1,2,3),listOf(4,5,6),
        listOf(7,8,9),listOf(1,5,9),listOf(3,5,7), listOf(1,4,7), listOf(2,5,8), listOf(3,6,9))
    var playVec1= mutableListOf<Int>()
    var playVec2= mutableListOf<Int>()
    var playedpos=mutableListOf<Int>()
    var curr_player="p1";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createBoard()
    }

   private fun createBoard()
    {
        val bd=findViewById<LinearLayout>(R.id.board)
        val par1=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1F)
        val par2=LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1F)
        par2.setMargins(8,8,8,8)
        var counter=1;
        for(i in 1..3)
        {
            val ll=LinearLayout(this@MainActivity)
            ll.orientation=LinearLayout.HORIZONTAL
            ll.id=100+i
            ll.layoutParams=par1
            for (j in 1..3)
            {
                val butt= Button(this@MainActivity)
                butt.id=counter
                butt.layoutParams=par2
                butt.textSize= 24F
                butt.setOnClickListener()
                {
                 setpos(it,i)
                }
                ll.addView(butt)
                counter+=1;
            }
            bd.addView(ll)
        }

        val rest=findViewById<Button>(R.id.restart)
        rest.setOnClickListener()
        {
            restart()
        }

    }
    private fun setpos(it:View,i:Int)
    {
        if (curr_player=="p1")
        {
            //X
            val pos=it.id
            val lay=findViewById<LinearLayout>(R.id.board).findViewById<LinearLayout>(100+i)
            val temp=lay.findViewById<Button>(pos)
            if (!playedpos.contains(pos))
            {
                temp.text = "X";
                playVec1.add(pos)
                playedpos.add(pos)
                setOutCome(curr_player)
                // curr_player="p2";
            }


        }
        else
        {
            //O
            val pos=it.id
            val lay=findViewById<LinearLayout>(R.id.board).findViewById<LinearLayout>(100+i)
            val temp=lay.findViewById<Button>(pos)
            if (!playedpos.contains(pos))
            {
                temp.text = "O";
                playVec2.add(pos)
                playedpos.add(pos)
                setOutCome(curr_player)
                //curr_player="p1"
            }
        }
    }
   private fun restart()
   {
       playVec1.clear();
       playVec2.clear();
       playedpos.clear()
       var ct=1;
       val bd=findViewById<LinearLayout>(R.id.board)
       for (i in 1..3)
       {
           val tt=bd.findViewById<LinearLayout>(100+i)
           for (j in 1..3)
           {
               val temp=tt.findViewById<Button>(ct)
               temp.setText("");
               ct+=1
           }
       }
       curr_player="p1"
   }
    private fun setOutCome(player:String)
    {
        if (player=="p1")
        {
            //check if won
            if (Winable("p1"))
            {
                val text=findViewById<TextView>(R.id.textView)
                val pl1=findViewById<EditText>(R.id.player1)
                text.setText("${pl1.text} WON")
            }
            //check if draw
            else if (Draw())
            {   val text=findViewById<TextView>(R.id.textView)
                text.setText("DRAW")
            }
            //else
            else
            {
                val text=findViewById<TextView>(R.id.textView)
                val pl2=findViewById<EditText>(R.id.player2)
                text.setText("${pl2.text} Turn to play")
                curr_player="p2"
            }
        }
        else
        {
            if (Winable("p2"))
            {
                val text=findViewById<TextView>(R.id.textView)
                val pl2=findViewById<EditText>(R.id.player2)
                text.setText("${pl2.text} WON")
            }
            //check if draw
            else if (Draw())
            {
                val text=findViewById<TextView>(R.id.textView)
                text.setText("DRAW")
            }
            //else
            else
            {
                val text=findViewById<TextView>(R.id.textView)
                val pl1=findViewById<EditText>(R.id.player1)
                text.setText("${pl1.text} Turn to play")
                curr_player="p1"
            }
        }
    }
    private fun Winable(player:String):Boolean
    {
        if (player=="p1")
        {
            for (i in Winning)
            {
                if(playVec1.containsAll(i)) {
                    return true
                }
            }
            return false
        }
        else
        {
            for (i in Winning)
            {
                if(playVec2.containsAll(i)) {
                    return true
                }
            }
            return false
        }
    }
    private fun Draw():Boolean
    {
        return playedpos.size==9
    }


}