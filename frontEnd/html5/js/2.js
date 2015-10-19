var country = 'Germany',
    weather,
    food,
    currency,
    message;

if (country) {
    if (country == 'England') {
        weather = 'horrible';
        food = 'filling';
        currency = 'pound sterling';
  }

    if (country == 'France') {
        weather = 'nice';
        food = 'stunning, but hardly ever vegetarian';
        currency = 'funny, small and colourful';
    }

    if (country == 'Germany') {
        weather = 'average';
        food = 'wurst thing ever';
        currency = 'funny, small and colourful';
    }

    message = 'this is ' + country +
              ', the weather is ' + weather +
              ', the food is ' + food +
              ' and the currency is ' + currency;
    alert(message);
}
