package presentation.controllers;

import dao.Database;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.adminFrames.agences.AgencesFrame;
import presentation.views.adminFrames.clients.ClientsFrame;
import presentation.views.clientsFrames.ClientMainFrame;

public class Test {
    public static void main(String[] args) {
        new AdminChoixCRUDFrame();
        //new ClientsFrame();
        //new ClientMainFrame(Database.getCompteDAO().findById("b-4-5"));
        //new AgencesFrame();
        /*
        List<Compte> c = Arrays.asList(
                new Compte(agenceDao.findById(1L), 100D, clientDao.findById(9L)),
                new Compte(agenceDao.findById(2L), 100D, clientDao.findById(9L)),
                new Compte(agenceDao.findById(2L), 150D, clientDao.findById(10L))
        );

        compteDao.saveAll(c);
        compteDao.findAll().forEach(System.out::print);
*/

        //System.out.println(compteDao.findById("b-2-2"));
/*
        List<Compte> lst = compteDao.findAll();
        lst.add(new Compte(agenceDao.findById(4L), 8000D, clientDao.findById(9L)));
        compteDao.saveAll(lst);*/
/*
        Compte c = compteDao.findById("b-2-2");
        c.setSolde(55D);
        compteDao.update(c);*/

        //System.out.println(compteDao.deleteById("b-2-2"));
        //compteDao.findByKeywordLike("najib").forEach(System.out::print);
    }
}
