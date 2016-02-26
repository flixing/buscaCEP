require 'calabash-android/calabash_steps'

  Given(/^I press the Buscar Cep button$/) do
    touch("android.widget.Button id:'btnCep'")
  end

  When(/^entering the zip code data$/) do
    enter_text("android.widget.EditText id:'edtCep'", '05433001')
  end

  When(/^I press the buscar button$/) do
    touch("android.widget.Button id:'btnChamaBuscaCEP'")
    sleep 03
  end
