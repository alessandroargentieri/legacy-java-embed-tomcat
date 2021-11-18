package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException, LifecycleException {

        Tomcat tomcat = tomcatBaseConfiguration();
        Context ctx = setContext(tomcat);
        setJarScanner(ctx);
        // non prende correttamente il contesto
        ctx.setConfigFile(new File("src/main/webapp/META-INF/context.xml").toURI().toURL());
        enableAnnotatedClasses(ctx);
        tomcat.start();
        tomcat.getServer().await();

    }

    private static Tomcat tomcatBaseConfiguration() {
        Tomcat tomcat = new Tomcat();
        String baseDir = ".";
        tomcat.setPort(8080);
        tomcat.setBaseDir(baseDir);
        tomcat.getHost().setAppBase(baseDir);
        tomcat.getHost().setAutoDeploy(true);
        tomcat.enableNaming();
        return tomcat;
    }

    // it let's the web.xml to be correctly acquired
    private static Context setContext(Tomcat tomcat) {
        //ContextConfig cc = new ContextConfig();
        //cc.setDefaultWebXml("src/main/webapp/WEB-INF/web-fragment.xml");
        // con addWebApp recupera correttamente il web.xml
        //Context ctx = tomcat.addWebapp(tomcat.getHost(), "/", "src/main/webapp", cc);
        Context ctx = tomcat.addWebapp(tomcat.getHost(), "/", "src/main/webapp");
        // IMPORTANT: if you want to pass another custom web.xml (and not the one in the default folder src/main/webapp/WEB-INF/web.xml)
        //ctx.getServletContext().setAttribute(Globals.ALT_DD_ATTR, "src/main/webapp/WEB-INF/alternative-web.xml");
        return ctx;
    }

    private static void setJarScanner(Context ctx) {
        // ((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);
        final StandardJarScanner jarScanner = new StandardJarScanner();
        jarScanner.setScanAllDirectories(true);
        jarScanner.setScanAllFiles(true);
        jarScanner.setScanBootstrapClassPath(true);
        jarScanner.setScanClassPath(true);
        jarScanner.setScanManifest(false);
        ctx.setJarScanner(jarScanner);
    }

    private static void enableAnnotatedClasses(Context ctx) {
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
    }

}
