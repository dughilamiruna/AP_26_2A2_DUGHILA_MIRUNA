package org.example;

import java.sql.Date;

public class Main {
    public static void main(String args[]) {
        try {
            ActorDAO actorDao = new ActorDAO();
            MovieDAO movieDao = new MovieDAO();
            MovieActorDAO movieActorDao = new MovieActorDAO();

            Actor actor1 = actorDao.findByName("Robert Pattinson");
            if (actor1 == null) {
                actorDao.create(new Actor(null, "Robert Pattinson"));
                actor1 = actorDao.findByName("Robert Pattinson");
            }
            Actor actor2 = actorDao.findByName("Kristen Stewart");
            if (actor2 == null) {
                actorDao.create(new Actor(null, "Kristen Stewart"));
                actor1 = actorDao.findByName("Kristen Stewart");
            }

            if (movieDao.findByTitle("Twilight") == null) {
                Movie movie1 = new Movie(null, "Twilight", Date.valueOf("2008-11-21"), 122, 5.4, 4);
                movieDao.create(movie1);
            }

            Actor actor3 = actorDao.findByName("Tom Holland");
            if (actor3 == null) {
                actorDao.create(new Actor(null, "Tom Holland"));
                actor3 = actorDao.findByName("Tom Holland");
            }

            Actor actor4 = actorDao.findByName("Zendaya");
            if (actor4 == null) {
                actorDao.create(new Actor(null, "Zendaya"));
                actor4 = actorDao.findByName("Zendaya");
            }

            if (movieDao.findByTitle("Spider-Man:No Way Home") == null) {
                Movie movie2 = new Movie(null, "Spider-Man:No Way Home", Date.valueOf("2021-12-13"), 188, 8.1, 1);
                movieDao.create(movie2);
            }

            Integer movieId = movieDao.findByTitle("Spider-Man:No Way Home");
            if (movieId != null && actor3 != null && actor4!=null) {
                movieActorDao.addActorToMovie(movieId, actor3.getId());
                movieActorDao.addActorToMovie(movieId, actor4.getId());
                System.out.println("Actorul a fost adăugat în film!");
            }

            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
