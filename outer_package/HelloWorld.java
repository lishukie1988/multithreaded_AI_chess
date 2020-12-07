package outer_package;

import com.example.helloword.HelloWorldTwo;
import outer_package.inner_package.*;
public class HelloWorld {

    public static void main(String[] arg) {
        HelloWorldTwo.exported();
        InsideClass.exported();

    }
}
