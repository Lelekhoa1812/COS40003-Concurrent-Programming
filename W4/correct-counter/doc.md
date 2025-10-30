// title
title Week 4 – Correct Counters: Atomics vs. Mutex

// actors
t1 [icon: activity, color: green]{ name string = "Thread T1" }
t2 [icon: activity, color: green]{ name string = "Thread T2" }
tN [icon: activity, color: green]{ name string = "Thread Tn" }

// shared counters
atomic_counter [icon: hash, color: teal]{
  name string = "Atomic Counter"
  ops string = "Atomic inc/get; lock-free"
  pros string = "Fast under low/moderate contention"
  cons string = "Hot-spot under high contention"
}

mutex_counter [icon: lock, color: purple]{
  name string = "Mutex-Protected Counter"
  ops string = "Lock acquire; increment; release"
  pros string = "Simple correctness"
  cons string = "Contention; potential convoying"
}

collector [icon: bar-chart-2, color: pink]{
  name string = "Metrics Collector"
  tracks string = "Rates, contention, tail latencies"
}

// relationships
t1 > atomic_counter
t2 > atomic_counter
tN > atomic_counter

t1 > mutex_counter
t2 > mutex_counter
tN > mutex_counter

atomic_counter > collector
mutex_counter > collector
