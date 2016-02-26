# encoding: utf-8
#!/usr/bin/env ruby

Before do
  @cep_valido = Faker::Base.numerify('05433-0##').to_s
  puts "O cep em questão é: " +@cep_valido.to_s
end
