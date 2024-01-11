package com.byurin.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.byurin.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener { calculateTip() }
    }

    // 팁 계산하고 화면에 표시하는 함수
    private fun calculateTip() {
        // 입력된 비용 문자열로 가져오기
        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDoubleOrNull()
        // 입력값이 없거나 0.0인 경우, 0.0으로 팁 표시하고 함수 종료
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        // 선택한 라디오 버튼에 따라 팁 백분율 할당
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        // 팁 계산
        var tip = tipPercentage * cost
        // 팁 올림할 경우
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.c수il(tip)
        }
        // 계산된 팁 화면에 표시
        displayTip(tip)
    }

    // 팁을 화면에 표시하는 함
    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}