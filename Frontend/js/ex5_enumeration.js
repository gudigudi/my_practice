var fruit = {
  apple: 2,
  orange: 5,
  pear: 1
},
sentence = 'I have',
quantity;

for (var kind in fruit) {
    quantity = fruit[kind];
    sentence += quantity + ' ' + kind +
                (quantity === 1 ? '' : 's') + ', ';
}

sentence = sentence.substr(0, sentence.length-2) + '.';

