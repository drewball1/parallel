use std::thread;
use std::sync::{Arc, Mutex};
use std::time::Instant;
const MAX_SIZE: usize = 100000001;

fn main() {
        run_sieve(100000000);
}


fn run_sieve(n: u64){
    let is_prime: Arc<Mutex<Vec<bool>>> = Arc::new(Mutex::new(vec![true;MAX_SIZE]));
    let mut prime_list: Vec<u64> = Vec::new();
    let x: u64 = 2;
    let counter = Arc::new(Mutex::new(x));
    let s: u64 = 4999999949999999;
    let sum = Arc::new(Mutex::new(s));
    let np: u64 = 99999998;
    let num = Arc::new(Mutex::new(np));
    //let (tx, rx) = mpsc::channel();
    let now = Instant::now();
    for _i in 1..9{
        let my_counter = counter.clone();
        let prime = is_prime.clone();
        let summer = sum.clone();
        let nummer = num.clone();
        let handle =  thread::spawn(move|| {
                let mut c = my_counter.lock().unwrap();
                let mut p = prime.lock().unwrap();
                let mut s1 = summer.lock().unwrap();
                let mut n1 = nummer.lock().unwrap();
                while (*c as i64) < (((n as f64).sqrt()) as i64+1){
                    let j = *c;
                    *c += 1;
                        if (*p)[j as usize] == true{
                            for k in j..n{
                                if k*j >= n{
                                    break;
                                }
                                else{
                                    if (*p)[(k * j) as usize] == true{
                                        (*p)[(k * j) as usize] = false;
                                        *s1 -= k * j;
                                        *n1 -= 1;
                                    }
                                }
                            }
                        }
                }
        });
        _ =handle.join();

    }
    let is_prime = & mut *is_prime.lock().unwrap();
    let sum = *sum.lock().unwrap();
    let num = *num.lock().unwrap();
    let new_now = Instant::now();
        let mut fun = 0;
            for i in (1..n).rev(){
                if fun < 10{
                    if is_prime[i as usize] == true{
                        prime_list.insert(0,i);
                        fun += 1;
                    }
                }
            }
    println!("Time Elapsed: {:?} Num Primes: {:?} Sum: {:?}",new_now.duration_since(now), num, sum);
    println!("Top Ten: {:?}",prime_list);

}






