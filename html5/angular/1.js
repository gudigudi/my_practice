import * as core from 'angular/core';

class Item {
    text:string;
    constructor(text) {
        this.text = text;
    }
}

@Component({
    selector: 'todos',
    template: new TemplateConfig({
        url: 'todos.html'
        uses: [core.directives]
    })
})

class Todos {
    todos:List<Item>;
    constructor() {
        this.todos = [new Item('Take over the World')];
    }
}


