
class SampleClassTests {
    // Your task is to set up TestUtils and instantiate the instance field.
    SampleClass instance;


   // @BeforeAll
    static void beforeAll() {
        //TestUtils testUtils = new TestUtils();
        TestUtils.timeConsumingSetup();
    }

    //@AfterAll
    static void afterAll() {
    }

    //@BeforeEach
    void beforeEach() {
        instance = TestUtils.getSampleClassInstance();
    }

    // @AfterEach
    void afterEach() {

    }

    //@Test
    void testMethodOne() {
        Assertions.assertTrue(instance.methodOne());
    }

    //@Test
    void testMethodTwo() {
        Assertions.assertTrue(instance.methodTwo());
    }
}

/*
class SampleClass {

    public boolean methodOne() {
        // Implementation details
        return true;
    }

    public boolean methodTwo() {
        // Implementation details
        return true;
    }
}

class TestUtils {

    static SampleClass getSampleClassInstance() {
        // implementation details
        return new SampleClass();
    }

    static void timeConsumingSetup() {
        // implementation details
    }
}

 */