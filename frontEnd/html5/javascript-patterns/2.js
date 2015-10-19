var global_var = 1;
global_novar = 2;
(function () {
    global_fromfunc = 3;
}());

delete global_var;
delete global_novar;
delete global_fromfunc;

typeof global_var;
typeof global_novar;
typeof global_fromfunc;
