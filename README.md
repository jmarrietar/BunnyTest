# BunnyTest: Palindrome challenge
---------------------- 

### Table of Contents
**[How I did it:](#how-i-did-it:)**  
**[CODE](#code)**  


##How I did it:  

#####Here is my code -> [CODE LINK](../master/src/java/com/virchgrave/serviceResource.java) 

I made the decision to use Java as my language for the algorithm and  later did a little research on how to make REST in JAVA with apache and did the configurations.<sup>[1]</sup>

My approach was first make a function that check if a number is a **Palindrome**. Second, I implemented a  **for** sentence, from x to y numbers and check in each one if the number is a palindrome, if that is the case, it will check if the binary of that number is a *palindrome* too. If the **two** conditions are true it is added to a Json Object (number in binary and decimal format).Lastly, I count the times this happens in total  and added this information to the Json Object as well. When the for condition ends, it returns the Json object. 

##CODE
I used two built in function in java that reverse strings and another that convert decimal to binary. 


######IsPalindrome function 
It returns true if number is a palindrome and false otherwise.

```java
    public static boolean isPalindrome(String numero) {
        StringBuffer NumeroBuffer = new StringBuffer(numero);
        String reverse = NumeroBuffer.reverse().toString();
        if (numero.equals(reverse)) {
            return true;
        }
        return false;
    }
```

######Main function and json Object 
It resieves Json format with x and y values and check palindromes(in decimal and binary format) between that range. The result is in JSON format. 

```java
        JSONObject JoseObject = new JSONObject(content);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectRespuesta = new JSONObject();

        
        TreeMap palindromos = new TreeMap();
        
        int cicle = 0;
        int count = 0;
        int inicio = Integer.parseInt(JoseObject.getString("x"));
        int fin = Integer.parseInt(JoseObject.getString("y"));
        for (int i = inicio; i <= fin; i++) {
            cicle++;
            if (isPalindrome(String.valueOf(i))) {                    // check if number is palindrome 
                String binario = Integer.toString(i, 2);
                if (isPalindrome(binario)) {                          // check if binary of number is palindrome 
                    palindromos.put(Integer.toString(i), binario);    // add 
                    count++;
                }
            }
        }

        jsonObjectRespuesta.put("Palindromos", palindromos);
        jsonObjectRespuesta.put("Numero Palindromos", count);
        jsonObjectRespuesta.put("Numero Ciclos", cicle);
```


##Complexity 
The Complexity of my Algorithm is : 

#POST request
Via curl and POST method it is send a JSON format with the values of x and y witch will be the minimum and maximum numbers the algorithm will search palindromes. 


```batch
curl -X POST -H "Content-Type: application/json" -d '{"x":"0","y":"1000000"}' http://localhost:8080/BunnyTest/webresources/service
```


<div align="center">
 <img src="https://github.com/jmarrietar/BunnyTest/blob/master/images/curl.png" width="50%" height="50%"/>
</div>
<br>

##Result 
It is returned to me in a JSON format the Total Number of Palindromes found. The values of the Palindromes numbers found in Decimal and Binary format and the total cycles needed. 

<div align="center">
<img src="https://github.com/jmarrietar/BunnyTest/blob/master/images/json.png" width="50%" height="50%"/>
</div>
<br>

##Feedback on test: 
I code this mostly on nights but I feel this test was very fun and kind of challenge because i needed to pick up pieces of information i didn't remembered very well (POST, CURL , Algorithm Complexity ) and put it all together to get something kind of working in a short period of time. 


##Resources 
[1] [RESTful Java Web Services with NetBeans, Jersey and Tomcat](http://vichargrave.com/restful-web-service-development-with-netbeans-and-tomcat-tutorial/)


##ANEXO 1 
-------

I use some Java Functions, so in order to know What the complexity of my algorithm was i needed first to check complexity for this java Functions. 

The two Java Functions  code and  their complexity is listed below: 