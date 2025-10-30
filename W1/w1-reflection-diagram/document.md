// title
title Week 1 – Computing Paradigms Concept Map

// core paradigms
concurrent [icon: share-2, color: blue]{
  name string = "Concurrent Computing"
  focus string = "Observer perspective: appears simultaneous"
  key_points string = "Time-sharing; Concurrency control (races, deadlocks)"
}

parallel [icon: cpu, color: orange]{
  name string = "Parallel Computing"
  focus string = "System perspective: truly simultaneous"
  key_points string = "Decompose tasks; combine results"
}

distributed [icon: network, color: purple]{
  name string = "Distributed Computing"
  focus string = "Autonomous nodes with private memory"
  key_points string = "Message passing; network latency"
}

// parallel exemplars
multi_core [icon: layers, color: green]{
  name string = "Multi-core CPU"
  note string = "Shared-memory parallelism"
}
gpu [icon: cpu, color: teal]{
  name string = "GPU Computing"
  note string = "Massively parallel kernels"
}

// distributed variants
cluster [icon: server, color: pink]{
  name string = "Cluster"
  note string = "Homogeneous; co-located; fast LAN"
}
grid [icon: globe, color: yellow]{
  name string = "Grid"
  note string = "Heterogeneous; geographically dispersed"
}
cloud [icon: cloud, color: indigo]{
  name string = "Cloud"
  note string = "SaaS / PaaS / IaaS; abstraction of infra"
}
edge [icon: radio, color: red]{
  name string = "Fog / Edge"
  note string = "Near data source; low latency; IoT"
}

// concurrency elements
timesharing [icon: clock, color: gray]{
  name string = "Time-sharing"
}
conc_control [icon: alert-triangle, color: gray]{
  name string = "Concurrency Control"
  risks string = "Race conditions, deadlocks"
}

// relationships (conceptual map)
parallel ~ concurrent        // related perspectives on simultaneity
distributed ~ concurrent     // distributed systems are often concurrent

multi_core > parallel
gpu > parallel

cluster > distributed
grid > distributed
cloud > distributed
edge > distributed

timesharing > concurrent
conc_control > concurrent
