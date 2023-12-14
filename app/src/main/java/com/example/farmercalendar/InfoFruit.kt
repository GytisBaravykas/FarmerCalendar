package com.example.farmercalendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.IntoFruitBinding

class InfoFruit : AppCompatActivity() {
    private lateinit var binding: IntoFruitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntoFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.myToolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.titleWiki.text = getString(R.string.Agrastas)
        binding.titleWiki.visibility = View.VISIBLE


        binding.textWiki.visibility = View.VISIBLE
        binding.textWiki.text = "Agrastai auga beveik kiekviename sode. Agrastuose yra 5-15 proc. cukraus, 1-2 proc. organinių rūgščių, taip pat fosforo, geležies, kalcio."

        binding.textWiki1.visibility = View.VISIBLE
        binding.textWiki1.text = "Derėti pradeda jau 1-2 metais, pats didžiausias derlius būna 6-7 metais. Gerai prižiūrimi krūmai išaugina po 25-30 kg uogų. Palankiomis sąlygomis agrastai dera 20-25 metus."

        binding.textWiki2.visibility = View.VISIBLE
        binding.textWiki2.text = "Agrastai nepakenčia aukšto gruntinio vandens ir užpelkėjusių dirvų. Vieta turi būti saulėta, atvira, kad nuo krūmų greitai nudžiūtų drėgmė. Dirvą būtina giliai ir gerai įdirbti, patręšti mėšlu. Geriausiai agrastai auga humusingose molio dirvose."

        binding.textWiki3.visibility = View.VISIBLE
        binding.textWiki3.text = "Geriausia agrastus sodinti rudenį, nes labai anksti pražysta. Atstumas tarp eilių turi būti nuo 1-2 m iki 0,5-0,75 cm. Iškart naujo krūmo formavimui atrenkami 3-4 sveikiausi ūgliai, kurie patrumpinami paliekant 3-4 pumpurus (jei krūmelis silpnas – 2 pumpurus). Kiti ūgliai išpjaunami. Vėliau, kol galutinai susiformuoja krūmai kiekvienais metais paliekami 3-5 stipriausi ūgliai. Jų, stipresnių šoninių ūglių viršūnėlės nupjaunamos iki pumpuro."

        binding.textWiki4.visibility = View.VISIBLE
        binding.textWiki4.text = """Gerai besikrūmijantiems augalams pagrindinių ūglių kiekį (15-20) galima suformuoti per 4-5 metus, lėčiau besikrūmijantiems – per 5-6 metus. Greičiausiai sensta pagrindinių stiebų viršūnėlės, todėl jas būtina nupjauti iki
                   stipraus, į viršų kylančio šoninio ūglio. Derančius krūmus reikia apkiTpti kiekvienais metais.
                """.trimIndent()
    }
}