package cliapp

import spock.lang.Specification
import spock.lang.Unroll

class AppRunnerSpec extends Specification {

    PrintStream originalOut
    ByteArrayOutputStream out

    void setup(){
        originalOut = System.out
        out = new ByteArrayOutputStream()
        System.setOut(new PrintStream(out))
    }

    void cleanup() {
        System.setOut(originalOut)
    }

    def 'when options and arguments are OK, invoke AppCore run method and return'() {
        given:
            AppCore appCoreMock = Mock()
            AppRunner testObj = new AppRunner(['-t', '2', 'aWord'] as String[])
            testObj.appCore = appCoreMock
        when:
            testObj.run()
        then:
            1 * appCoreMock.printWord('aWord', 2)

    }

    def 'when required options are not specified, print usage and exit'() {
        given:
            AppRunner testObj = new AppRunner([] as String[])
        when:
            testObj.run()
        then:
            usageIsPrinted(true)
    }

    @Unroll
    def 'when unrecognized option (#args) is specified, print usage and exit'() {
        given:
            AppRunner testObj = new AppRunner(args)
        when:
            testObj.run()
        then:
            usageIsPrinted(true)
        where:
            args << ['--unrecognized', '-h somejunk']
    }

    @Unroll
    def 'when mandatory options are specified but number of arguments is incorrect, print usage and exit'(){
        given:
            AppRunner testObj = new AppRunner((['--times', '2',] + badArgs) as String[])
        when:
            testObj.run()
        then:
            usageIsPrinted()
        where:
            badArgs << [[],
                        ['too', 'many', 'arguments']]
    }

    public static final String USAGE = '''usage: printwords [options] [word]
Sample app that prints one word a given number of times
 -t,--times <number-times>   Number of times the word will be printed.'''

    public static final String USAGE_WITH_ERROR = 'error: Missing required option: t\n' + USAGE

    private boolean usageIsPrinted(boolean withError = false) {
        assert out.toString().trim() == (withError ? USAGE_WITH_ERROR : USAGE)
        true
    }
}
