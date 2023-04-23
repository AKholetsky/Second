| collection & java version   | 20                        | 11                                    | 8                           |
|-----------------------------|---------------------------|---------------------------------------|-----------------------------|
| Collections.synchronizedMap | cold = 25 ms hot = 6 ms   | cold = 32 ms hot = 10 ms              | cold = 60 ms  hot = 10 ms   |                  
| ConcurrentHashMap           | cold = 44 ms hot = 10 ms  | cold = 47 ms  hot = 12 ms             | cold = 74 ms hot = 12 ms    |                  
| HashMap with synchronize    | cold = 29 ms hot = 8 ms   | cold =  45 ms hot = 12 ms             | cold = 61 ms hot = 8 ms     |                  
