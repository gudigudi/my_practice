def capitalizeAll(args: String*) = {
    args.map {
        arg => arg.capitalize
    }
}

capitalizeAll("rarity", "applejack");

