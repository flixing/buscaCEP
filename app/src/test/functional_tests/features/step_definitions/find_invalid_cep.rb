# encoding: utf-8
#!/usr/bin/env ruby

When(/^entering the invalid zip code data$/) do
  enter_text("android.widget.EditText id:'edtCep'", @invalid_cep)
end
