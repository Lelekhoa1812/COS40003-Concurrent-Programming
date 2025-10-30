// title
title Week 3 – Scheduling Algorithms Overview

// scheduling types
fcfs [icon: list, color: blue]{
  name "First Come, First Served (FCFS)"
  type "Non-preemptive"
  pros "Simple; fair in arrival order"
  cons "Poor response for short jobs after long ones"
  starvation "Low"
}

sjf [icon: clock, color: orange]{
  name "Shortest Job First (SJF)"
  type "Non-preemptive"
  pros "Optimal average wait time"
  cons "Needs burst estimation; starvation risk"
  starvation "Yes"
}

srtf [icon: clock, color: red]{
  name "Shortest Remaining Time First (SRTF)"
  type "Preemptive"
  pros "Optimal average wait; responsive to short jobs"
  cons "High context-switch overhead; starvation risk"
  starvation "Yes"
}

rr [icon: rotate-ccw, color: purple]{
  name "Round Robin (RR)"
  type "Preemptive"
  pros "Good responsiveness; fair sharing"
  cons "Higher overhead with small quantum"
  starvation "No"
}

lottery [icon: gift, color: teal]{
  name "Lottery Scheduling"
  type "Preemptive"
  pros "Flexible; easy to add/remove jobs"
  cons "Randomness can cause short-term variance"
  starvation "Low"
}

mlfq [icon: layers, color: green]{
  name "Multi-Level Feedback Queue (MLFQ)"
  type "Preemptive"
  pros "Balances interactivity & throughput"
  cons "Complex to tune; possible starvation without boosts"
  starvation "Yes (mitigated by boosts)"
}

// conceptual links
sjf ~ srtf       // related shortest-job strategies
rr ~ mlfq        // MLFQ often uses RR within levels
lottery ~ mlfq   // both adaptable, fair-share oriented
