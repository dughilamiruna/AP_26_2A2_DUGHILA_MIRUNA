
public class Main {
    public static void main(String[] lang) {

    String[] limbaj = {"C", "C++", "C#", "Python",
            "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    int n = (int) (Math.random() * 1_000_000);
    n=n*3; n=n+21;
    n=n+255;
    n=n*6;

    int s=0;
    while(n>=10)
    {
        while (n>0)
        {
            s=s+n%10;
            n=n/10;
        }
        n=s; s=0;
    }
    System.out.println("Willy-nilly, this semester I will learn " + limbaj[n]);
    }
}
