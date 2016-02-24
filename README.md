# BunnyTest: Palindrome challenge
---------------------- 

##My Solution:  

#####Here is my code -> [CODE LINK](../master/src/java/com/virchgrave/serviceResource.java) 

I made the decision to use Java as my language for the algorithm and needed to make some little research on how to make REST in JAVA with Apache and what it configuration was.<sup>[1]</sup>

My approach was first, make a function that check if a number is a **Palindrome**. Second, I implemented a  **For** sentence, from x to y numbers and check in each one if the number is a palindrome, if that is the case, it will check if the binary of that number is a *palindrome* too. If the **two** conditions are true the number is added to a Json Object (in decimal an binary format) .Lastly, I count the times this procedure happens in total  and added this information to the Json Object as well. When the for condition ends, it returns the Json object. 

##CODE

Inside my Code used two built in function in java.One function to reverse strings and another function that convert decimal to binary. 


######IsPalindrome function 
This function receives a number in a string format and uses a Java built in function to reverse the string and check if it is a Plindrome. 
It returns true if number is a palindrome and false otherwise.

```java
    public static boolean isPalindrome(String numero) {
        StringBuffer NumeroBuffer = new StringBuffer(numero);      // executed in constant 1
        String reverse = NumeroBuffer.reverse().toString();        // executed in 12n+9 -> See Anexo1
        if (numero.equals(reverse)) {                              // executed in constant 1 
            return true;                                           // executed in constant 1 
        }
        return false;                                               // executed in constant 1 
    }
```
The Complexity for this function is <br>
    f(n)= 1+12n+9+1+1+1 <br>
    f(n)= 12n+13 <br>
    O(n)=n <br>

######Main function and json Object 
It resieves Json format with x and y values and check palindromes(in decimal and binary format) between that range. The result is returned in JSON format. 

```java
        JSONObject JoseObject = new JSONObject(content);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectRespuesta = new JSONObject();

        
        TreeMap palindromos = new TreeMap();
        
        int cicle = 0;                                               // executed in constant time 1
        int count = 0;                                               // executed in constant time 1
        int inicio = Integer.parseInt(JoseObject.getString("x"));    // executed in constant time 1
        int fin = Integer.parseInt(JoseObject.getString("y"));       // executed in constant time 1

                                                                //loop will be executed n 
        for (int i = inicio; i <= fin; i++) {
            cicle++;                                             // executed in constant time 1
            if (isPalindrome(String.valueOf(i))) {               // executed in 12n+13 time  check if number is palindrome 
                String binario = Integer.toString(i, 2);            //executed in logn + 10 
                if (isPalindrome(binario)) {                          //executed in 12n+13 time  check if binary of number is palindrome 
                    palindromos.put(Integer.toString(i), binario);     // executed in constant time 1
                    count++;                                             // executed in constant time 1
                }
            }
        }

        jsonObjectRespuesta.put("Palindromos", palindromos);
        jsonObjectRespuesta.put("Numero Palindromos", count);
        jsonObjectRespuesta.put("Numero Ciclos", cicle);
```
f(n)= 1+1+1+1+n(1+12n+13+logn+10 +12n+13+1+1)+1+1+1 <br>
f(n)= 7+n+12n^2+13n+nlogn+10n+12n^2+13n+n+n <br>
f(n)=24n^2+29n+nlogn <br>


##Complexity 
The Complexity of my Algorithm is O(n)= n^2+n+nlogn

##POST request
Via curl and POST method a JSON format is sent with the values of x and y witch will be the minimum and maximum numbers the algorithm will search palindromes. 

```batch
curl -X POST -H "Content-Type: application/json" -d '{"x":"0","y":"1000000"}' http://localhost:8080/BunnyTest/webresources/service
```


<div align="center">
 <img src="https://github.com/jmarrietar/BunnyTest/blob/master/images/curl.png" width="50%" height="50%"/>
</div>
<br>

##Result 
It is returned in a JSON format the Total Number of Palindromes found. The values of the Palindromes numbers found in Decimal and Binary format and the total cycles needed. 

<div align="center">
<img src="https://github.com/jmarrietar/BunnyTest/blob/master/images/json.png" width="50%" height="50%"/>
</div>
<br>

##Feedback on test: 
I code this mostly on nights but I felt this test was very fun and kind of challenge because i needed to pick up pieces of information i didn't remembered very well (POST, CURL , Algorithm Complexity ) and put it all together to get something kind of working in a short period of time. 


##Resources 
[1] [RESTful Java Web Services with NetBeans, Jersey and Tomcat](http://vichargrave.com/restful-web-service-development-with-netbeans-and-tomcat-tutorial/)


##ANEXO 1 
-------
I use some Java Functions, so in order to know What the complexity of my algorithm was i needed first to check complexity for this java Functions. 

The two Java Functions  code and  their complexity is listed below: 

```java
  public static String toString(int i, int radix) {

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)  // executed in constant time 1
            radix = 10;                                                  // executed in constant time 1

        /* Use the faster version */
        if (radix == 10) {                                               // executed in constant time 1
            return toString(i);                                           // executed in constant time 1
        }

        char buf[] = new char[33];                                          // executed in constant time 1
        boolean negative = (i < 0);                                         // executed in constant time 1
        int charPos = 32;                                                   // executed in constant time 1

        if (!negative) {                                                        // executed in constant time 1
            i = -i;                                                         // executed in constant time 1
        }

        while (i <= -radix) {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;                                                    //i become half in each iteration. Radix is 2. 
        }                                                                      // logn 
        buf[charPos] = digits[-i];

        if (negative) {                                                       // executed in constant time 1
            buf[--charPos] = '-';                                            // executed in constant time 1
        }

        return new String(buf, charPos, (33 - charPos));
    }

```

fn(n)=logn +10<br>


```java
/*Complexity Reverse */
 public AbstractStringBuilder reverse() {
        boolean hasSurrogate = false;                // executed in constant time 1
        int n = count - 1;                           // executed in constant time 1

        //loop will be executed n-1 times    
        for (int j = (n-1) >> 1; j >= 0; --j) {
            char temp = value[j];                    // executed in constant time 1
            char temp2 = value[n - j];               // executed in constant time 1
            if (!hasSurrogate) {                     // executed in constant time 1
                hasSurrogate = (temp >= Character.MIN_SURROGATE && temp <= Character.MAX_SURROGATE)    // executed in constant time 1
                    || (temp2 >= Character.MIN_SURROGATE && temp2 <= Character.MAX_SURROGATE);
            }
            value[j] = temp2;                        // executed in constant time 1
            value[n - j] = temp;                     // executed in constant time 1
        }

        if (hasSurrogate) {                           // executed in constant time 1
            // Reverse back all valid surrogate pairs

                     //loop will be executed n+1 times  
            for (int i = 0; i < count - 1; i++) {
                char c2 = value[i];                   // executed in constant time 1
                if (Character.isLowSurrogate(c2)) {   // executed in constant time 1
                    char c1 = value[i + 1];           // executed in constant time 1
                    if (Character.isHighSurrogate(c1)) {    // executed in constant time 1
                        value[i++] = c1;              // executed in constant time 1
                        value[i] = c2;                // executed in constant time 1
                    }
                }
            }
        }
        return this;
    }

```

The complexity for this Java routine is <br>
   f(n)= 1+1+n(1+1+1+1+1+1)+1+(n+1)(1+1+1+1+1+1) <br>
   f(n)=2+6n+1+(n+1)(6) <br>
   f(n)= 2+6n+1+6n+6 <br>
   f(n)= 12n+9 <br>
   O(n)=n  <br>


