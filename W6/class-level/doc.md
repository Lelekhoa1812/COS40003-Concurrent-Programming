// title
title Week 6 – Class-Level Lock for Static Data

static_data [icon: database, color: blue]{
  name string = "Static Resource"
  examples string = "Global counter, cache index"
}

class_lock [icon: lock, color: indigo]{
  name string = "Class-Level Lock"
  forms string = "synchronized(Class) / static ReentrantLock"
}

instances [icon: boxes, color: teal]{
  name string = "Multiple Instances"
  note string = "Do not rely on instance locks for static data"
}

metrics [icon: bar-chart-2, color: pink]{
  name string = "Observability"
  tracks string = "Hold time, contention, stalls"
}

instances > class_lock
class_lock > static_data
metrics <> class_lock
metrics <> static_data
