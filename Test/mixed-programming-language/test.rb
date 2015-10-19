class Person
  attr_reader :name, :age, :height

  def initialize(name, age, height)
    @name, @age, @height = name, age, height
  end
end

class Array
  def sort_by_attribute(sym)
    sort {|x,y| x.send(sym) <=> y.send(sym)}
  end
end

people = []
people << Person.new("Hansel", 35, 69)
people << Person.new("Gretel", 32, 64)

sort_by_name = people.sort_by_attribute :name
sort_by_age = people.sort_by_attribute :age

