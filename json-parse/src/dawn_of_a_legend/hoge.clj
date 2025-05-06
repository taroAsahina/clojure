(+ 1 4)


comment(
        
        (defn- plotxy [shape arg2 arg3])
          
        (def the-ansewer 42)
        the-ansewer
        

        (def my-stuff '("shiro" "kuro" "aka"))
        (my-stuff) 

     
        (def my-stuff2 ["shiro" "kuro" "aka"])
        my-stuff2 

        (defn messenger
          ([] (messenger "Hello World"))
          ([msg] (println msg))) 
        (messenger)
        (messenger "hoge")

        (defn hello [greeting & who]
          (println greeting who))
        (hello "hoge" "gu" "ri")

        ( (fn [message] (println "Hello " message)) "taro")

        (defn greet [name] (str "hello " name))
        (greet "taro")
        (def greet (fn [name] (str "Wello " name)))
        (greet "jiro")

        (defn plot [shape cords]
          (plotxy shape (first cords) (second cords)))
        (defn plot [shape cords]
          (apply plotxy shape cords))

        (apply + [1 2 3]) 
        [1 2 3]
        (max 1 2 3)
        (apply max '(2 5 3))
        (apply + '(1 2))
        (apply + 1 2 '(3 4))
        (def A [[1 2] [3 4]])
        (apply map vector A) 
        )
        
     