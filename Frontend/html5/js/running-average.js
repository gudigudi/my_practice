function makeRunningAverage(list, size) {
    return list.map(function (corrent, index, list){
        var start, end, win;

        /* find start and end points of the rolling average window */
        start = index - size < 0 ?
            0 :
            index - size;
        // extract that window
        end = index + size > list.length ?
            list.length :
            index + size;
        win = list.slice(start, end);  // take an average
        return win.reduce(function (accumulator, current) {
            return accumulator + current;
        }, 0) / (end - start);
    });
}
