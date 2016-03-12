import java.util.Scanner;

class Project4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("Please enter an infix expression: ");
      System.out.print("> ");
      String infix = sc.nextLine();

      infix = infix.replaceAll("\\s+", "");
      
      if (infix.isEmpty()) {
        System.out.println("Exiting program...");
        System.exit(0);
      }

      System.out.println();
      System.out.println("infix:     " + infix);
      System.out.println("postfix:   " + postfix(infix));
      System.out.println("prefix:    " + prefix(infix));
      System.out.println();
    }
  }

  static String postfix(String s) {
    String output = "";
    NodeStack<Character> ns = new NodeStack<Character>();
    char[] infix = s.toCharArray();

    for (char c: infix) {
      switch(c) {
        case '(':
          ns.push(c);
          break;
        case ')':
          if (!ns.contains('(')) {
            System.out.println("ERROR: extra parenthesis found. Exiting program.");
            System.exit(1);
          }
          while(!ns.top().equals('(')) {
            output += ns.pop();
          }
          ns.pop();
          break;
        case '*':
        case '/':
        case '+':
        case '-':
          while (!ns.isEmpty() && precedence(c) <= precedence(ns.top())) {
            output += ns.pop();
          }
          ns.push(c);
          break;
        default:
          output += c;
      }
    }

    while (!ns.isEmpty()) {
      if (ns.top().equals('(') || ns.top().equals(')')) {
        System.out.println("ERROR: extra parenthesis found. Exiting program.");
        System.exit(1);
      }
      output += ns.pop();
    }

    return output;
  }

  static String prefix(String s) {
    String output = "";
    NodeStack<String> operands = new NodeStack<String>();
    NodeStack<Character> operators = new NodeStack<Character>();
    char[] infix = s.toCharArray();

    for (char c: infix) {
      char op = ' ';
      String left = "";
      String right = "";
      String combine = "";
      switch(c) {
        case '(':
          operators.push(c);
          break;
        case ')':
          if (!operators.contains('(')) {
            System.out.println("ERROR: extra parenthesis found. Exiting program.");
            System.exit(1);
          }
          while (!operators.top().equals('(')) {
            op = operators.pop();
            right = operands.pop();
            left = operands.pop();
            combine = op + left + right;
            operands.push(combine);
          }
          operators.pop();
          break;
        case '*':
        case '/':
        case '+':
        case '-':
          while (!operators.isEmpty() && precedence(c) <= precedence(operators.top())) {
            op = operators.pop();
            right = operands.pop();
            left = operands.pop();
            combine = op + left + right;
            operands.push(combine);
          }
          operators.push(c);
          break;
        default:
          operands.push(String.valueOf(c));
      }
    }

    while (!operators.isEmpty()) {
      // check for left over parenthesis here
      if (operators.top().equals('(')) {
        System.out.println("ERROR: extra parenthesis found. Exiting program.");
        System.exit(1);
      }

      char op = operators.pop();
      String right = operands.pop();
      String left = operands.pop();
      String combine = op + left + right;
      operands.push(combine);
    }

    output = operands.pop();

    return output;
  }

  static int precedence(char c) {
    switch(c) {
      case '*':
      case '/':
        return 3;
      case '+':
      case '-':
        return 2;
      case '(':
        return 1;
      default:
        return 0;
    }
  }
}
