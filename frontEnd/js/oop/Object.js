var mikhail = Object.create( null );

Object.defineProperty( mikhail, 'name', {
	value: 'Mikhail',
	writabel: true,
	configurable: true,
	enumerable: true
} )

Object.defineProperty( mikhail, 'age', {
	value: 19,
	writabel: true,
	configurable: true,
	enumerable: true
} )

Object.defineProperty( mikhail, 'gender', {
	value: 'Male',
	writabel: true,
	configurable: true,
	enumerable: true
} )


// function getter & setter
function get_full_name() {
	return this.first_name + ' ' + this.last_name;
}

function set_full_name( new_name ) {
	var names;
	names = new_name.trim()
		.split( /\s+/ )
	this.first_name = names[ '0' ] || '';
	this.last_name = names[ '1' ] || '';
}

Object.defineProperty( mikhail, 'name', {
	get: get_full_name,
	set: set_full_name,
	configurable: true,
	enumerable: true
} )
