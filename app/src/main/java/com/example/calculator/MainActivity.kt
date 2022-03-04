package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // clear operation text view
        findViewById<TextView>(R.id.btn_ac).setOnClickListener {
            findViewById<TextView>(R.id.math_operation).text = ""
            findViewById<TextView>(R.id.result_text).text = ""
        }
        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            var math_operation = findViewById<TextView>(R.id.math_operation)
            var str = math_operation.text.toString()
            if (str.isNotEmpty()) {
                math_operation.text = str.substring(0, str.length - 1)
            }
            findViewById<TextView>(R.id.result_text).text = ""
        }

        // numbers listeners
        findViewById<TextView>(R.id.btn_0).setOnClickListener { appendOperation("0") }
        findViewById<TextView>(R.id.btn_1).setOnClickListener { appendOperation("1") }
        findViewById<TextView>(R.id.btn_2).setOnClickListener { appendOperation("2") }
        findViewById<TextView>(R.id.btn_3).setOnClickListener { appendOperation("3") }
        findViewById<TextView>(R.id.btn_4).setOnClickListener { appendOperation("4") }
        findViewById<TextView>(R.id.btn_5).setOnClickListener { appendOperation("5") }
        findViewById<TextView>(R.id.btn_6).setOnClickListener { appendOperation("6") }
        findViewById<TextView>(R.id.btn_7).setOnClickListener { appendOperation("7") }
        findViewById<TextView>(R.id.btn_8).setOnClickListener { appendOperation("8") }
        findViewById<TextView>(R.id.btn_9).setOnClickListener { appendOperation("9") }
        findViewById<TextView>(R.id.btn_left_bracket).setOnClickListener { appendOperation("(") }
        findViewById<TextView>(R.id.btn_right_bracket).setOnClickListener { appendOperation(")") }

        // action listeners
        findViewById<TextView>(R.id.btn_minus).setOnClickListener { appendOperation("-") }
        findViewById<TextView>(R.id.btn_plus).setOnClickListener { appendOperation("+") }
        findViewById<TextView>(R.id.btn_mul).setOnClickListener { appendOperation("*") }
        findViewById<TextView>(R.id.btn_division).setOnClickListener { appendOperation("/") }
        findViewById<TextView>(R.id.btn_dot).setOnClickListener { appendOperation(".") }

        // equal
        findViewById<TextView>(R.id.btn_equal).setOnClickListener {
            val math_operation = findViewById<TextView>(R.id.math_operation)
            val result_text = findViewById<TextView>(R.id.result_text)
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    result_text.text = longRes.toString()
                } else {
                    result_text.text = result.toString()
                }
            } catch (e: Exception) {
                Log.d("Ошибка: ", "Сообщение: ${e.message}")
                result_text.text = "Ошибка"
            }
        }
    }

    fun appendOperation(str: String) {
        val result_text = findViewById<TextView>(R.id.result_text)
        val math_operation = findViewById<TextView>(R.id.math_operation)
        if (result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }
        math_operation.append(str)
    }
}