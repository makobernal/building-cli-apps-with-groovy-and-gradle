package cliapp

class AppMain {

    static void main(String[] args) {
        new AppRunner(args).run()
    }
}

class AppRunner {

    OptionAccessor options
    CliBuilder cli

    AppCore appCore = new AppCore()

    AppRunner(String[] args){
        cli = new CliBuilder(usage: 'printwords [options] [word]', width: 120)
        cli.header = 'Sample app that prints one word a given number of times'
        cli.with{
            t longOpt: 'times', args:1 , argName: 'number-times', required: true, 'Number of times the word will be printed.'
        }

        options = cli.parse(args)
    }

    public void run() {

        if (!options) { //options is null, there has been a problem parsing args or options and usage has been printed
            return
        }

        if (options.arguments().size() != 1){// we only accept one arg, i.e. one word
            cli.usage()     //print usage and return
            return
        }

        try {
           appCore.printWord(options.arguments().first(), options.t as int)
        } catch (Exception e) { // Here we could be catching any checked exception thrown by the AppCore class
            println "Exception of type: ${e.class.canonicalName}"
            println "Exception with message: ${e.getMessage()}"
            return
        }
    }
}

class AppCore {

    public void printWord(String word, int times) {
        println word * times
    }
}

