# <hr align="center"> BunnyTest </hr> 

#Palindrome challenge:
---------------------- 

# Table of Contents
<b><a href="#Feedback">Feedback on test</a></b>
<b><a href="#features">Features</a></b>
<br>


##How I did it :  

Here is my code ->[CODE LINK](../blob/master/src/java/com/virchgrave/serviceResource.java) 

I made the decision to use Java as my language for the algorithm and  later needed to research a little how to make REST API in JAVA with apache. (Resources). 

First I made a function that check if a number is a palindrome. Second a for sentence the x to y numbers and check first if the decimal number if a palindrome, if that is the case, it will check if the binary of the number is a palindrome. If the two conditions are true it is added to a Json Object (number in binary and decimal format).Lastly, I count the times this happens in total  and added this information to the Json Object as well. When the for condition ends, i return the Json object. 

I used a built in function in java that reverse strings and another that convert decimal to binary. 

IsPalindrome function 
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

Main function and json Object 

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


#Complexity 
The Complexity of my Algorithm is : 


Via curl and POST method i send in a JSON format the values of x and y with will be the minimum and maximum numbers the algorithm will search palindromes. 

-> Code
-> Image

It is returned to me in a JSON format the Total Number of Palindromes found. The values of the Palindromes numbers found in Decimal and Binary format and the total cycles needed. 
-> Image 


##Feedback on test: 
I code this mostly on nights but I feel this test was very fun and kind of challenge because i needed to pick up pieces of information i didn't remembered very well (POST, CURL , Algorithm Complexity ) and put it all together to get something kind of working in a short period of time. 


Resources 
-
-



ANEXO 1 
-------

I use some Java Functions, so in order to know What the complexity of my algorithm was i needed first to check complexity for this java Functions. 

The two Java Functions  code and  their complexity is listed below: 