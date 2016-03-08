package cliapp

public class App {

    public static void main(String[] args) {
	    System.out.println("This app is using CliBuilder")

        def cli = new CliBuilder()

        cli.with {
            u longOpt: 'uno', 'An option named uno'
            d longOpt: 'dos', required: true, 'An option named dos'
            t longOpt: 'tres', args:2, valueSeparator: ',' as char
        }

        def options = cli.parse(args)

        if (options.arguments().isEmpty()) {
            println cli.usage()
        }

    }
}
