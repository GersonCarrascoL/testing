package pe.edu.unmsm.quipucamayoc.util;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.quipucamayoc.unmsm.quartz.ComprobanteTrigger;
import com.quipucamayoc.unmsm.quartz.CronTriggerTipoCambio;

public class MyAppServletContextListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(MyAppServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        CronTriggerTipoCambio tipoCambio = new CronTriggerTipoCambio();
        tipoCambio.iniciar();
        ComprobanteTrigger comprobante = new ComprobanteTrigger();
        comprobante.iniciar();
        logger.info("se ha iniciado el servlet para el tipo de cambio y comprobante");
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.info("se ha destruido el servlet para el tipo de cambio y comprobante");
    }
}