extern crate rand;

//use std::io;
use rand::Rng;

static MAX_HEALTH: i32=100;
static GAME_NAME: &'static str = "Monster Attack";

fn main(){
    println!("Hello,world!");

    let secret_number=rand::thread_rng().gen_range(1,101);
    println!("The number is: {}",secret_number);
}