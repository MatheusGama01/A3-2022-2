package ALL.TEST;

import CONTROLLER.TEST.INTEGRATION.ControllerTelaAdicionarTarefaTest;
import CONTROLLER.TEST.INTEGRATION.ControllerTelaCadastroTest;
import CONTROLLER.TEST.INTEGRATION.ControllerTelaHomeTest;
import CONTROLLER.TEST.INTEGRATION.ControllerTelaLoginTest;
import CONTROLLER.TEST.INTEGRATION.ControllerTelaTarefaTest;
import DAO.TEST.INTEGRATION.TarefaDAOTest;
import DAO.TEST.INTEGRATION.UsuarioDAOTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ControllerTelaAdicionarTarefaTest.class,
    ControllerTelaCadastroTest.class,
    ControllerTelaHomeTest.class,
    ControllerTelaLoginTest.class,
    ControllerTelaTarefaTest.class,
    TarefaDAOTest.class,
    UsuarioDAOTest.class
})
public class AllIntegrationTests {
    
}
