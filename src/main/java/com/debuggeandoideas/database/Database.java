package com.debuggeandoideas.database;

import com.debuggeandoideas.models.Console;
import com.debuggeandoideas.models.Review;
import com.debuggeandoideas.models.Videogame;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Database {

    public static Flux<Videogame> getDataAsFlux() {

        List<Videogame> videogames = Arrays.asList(


                Videogame.builder()
                        .name("Forza Horizon 5")
                        .price(50.06)
                        .console(Console.XBOX)
                        .reviews(List.of(
                                new Review("Gráficos impresionantes y mundo abierto asombroso", 5),
                                new Review("Un poco costoso, pero vale la pena", 4),
                                new Review("Excelente experiencia de conducción", 5)
                        ))
                        .officialWebsite("https://www.forzamotorsport.net")
                        .isDiscount(false)
                        .totalSold(80)
                        .build(),

                Videogame.builder()
                        .name("Resident Evil 4")
                        .price(55.20)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("Un clásico de terror y acción", 5),
                                new Review("Versión renovada muy bien lograda", 4),
                                new Review("Jugabilidad adictiva", 5),
                                new Review("Excelente rejugabilidad", 5)
                        ))
                        .officialWebsite("https://www.residentevil.com/re4")
                        .isDiscount(true)
                        .totalSold(96)
                        .build(),

                Videogame.builder()
                        .name("Assassin's Creed Origins")
                        .price(15.55)
                        .console(Console.DISABLED)
                        .reviews(List.of(
                                new Review("Ambientación en Egipto espectacular", 5)
                        ))
                        .officialWebsite("https://www.assassinscreed.com/origins")
                        .isDiscount(false)
                        .totalSold(65)
                        .build(),

                Videogame.builder()
                        .name("Assassin's Creed Odyssey")
                        .price(15.55)
                        .console(Console.DISABLED)
                        .reviews(List.of(
                                new Review("Entorno griego inmersivo", 5),
                                new Review("Historia extensa", 5),
                                new Review("Misiones secundarias muy divertidas", 5)
                        ))
                        .officialWebsite("https://www.assassinscreed.com/odyssey")
                        .isDiscount(false)
                        .totalSold(33)
                        .build(),

                Videogame.builder()
                        .name("Assassin's Creed Valhalla")
                        .price(30.33)
                        .console(Console.DISABLED)
                        .reviews(List.of(
                                new Review("Gran mejora en el sistema de combate", 4),
                                new Review("La ambientación vikinga es genial", 3),
                                new Review("Mapa muy amplio y detallado", 4),
                                new Review("Excelente banda sonora", 5)
                        ))
                        .officialWebsite("https://www.assassinscreed.com/valhalla")
                        .isDiscount(false)
                        .totalSold(78)
                        .build(),

                Videogame.builder()
                        .name("GTA 5")
                        .price(25.50)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("Libertad total y muchas actividades", 4)
                        ))
                        .officialWebsite("https://www.rockstargames.com/V")
                        .isDiscount(true)
                        .totalSold(140)
                        .build(),

                Videogame.builder()
                        .name("Crash Bandicoot 4")
                        .price(50.06)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("La nostalgia está de vuelta", 3),
                                new Review("Algunas mecánicas muy difíciles", 2)
                        ))
                        .officialWebsite("https://www.crashbandicoot.com")
                        .isDiscount(false)
                        .totalSold(188)
                        .build(),

                Videogame.builder()
                        .name("Forza Horizon 4")
                        .price(10.06)
                        .console(Console.XBOX)
                        .reviews(Collections.emptyList())
                        .officialWebsite("https://www.forzamotorsport.net")
                        .isDiscount(false)
                        .totalSold(52)
                        .build(),

                Videogame.builder()
                        .name("The Last of Us")
                        .price(40.06)
                        .console(Console.PLAYSTATION)
                        .reviews(List.of(
                                new Review("Una historia emocionalmente impactante", 5),
                                new Review("Esperando la tercera parte", 5)
                        ))
                        .officialWebsite("https://www.thelastofus.playstation.com")
                        .isDiscount(false)
                        .totalSold(41)
                        .build(),

                Videogame.builder()
                        .name("God of War")
                        .price(28.00)
                        .console(Console.PLAYSTATION)
                        .reviews(List.of(
                                new Review("Buen combate, pero prefiero la mitología griega", 3),
                                new Review("Cambios interesantes en la historia", 4),
                                new Review("Un poco corto pero intenso", 4)
                        ))
                        .officialWebsite("https://www.playstation.com/god-of-war")
                        .isDiscount(false)
                        .totalSold(79)
                        .build(),

                Videogame.builder()
                        .name("Halo")
                        .price(50.06)
                        .console(Console.XBOX)
                        .reviews(List.of(new Review("Shooter icónico, multijugador increíble", 4)))
                        .officialWebsite("https://www.halowaypoint.com")
                        .isDiscount(false)
                        .totalSold(14)
                        .build(),

                Videogame.builder()
                        .name("Super Mario Bros.")
                        .price(55.99)
                        .console(Console.NINTENDO)
                        .reviews(List.of(new Review("Clásico eterno, Nintendo nunca falla", 5)))
                        .officialWebsite("https://www.nintendo.com/mario")
                        .isDiscount(false)
                        .totalSold(64)
                        .build(),

                Videogame.builder()
                        .name("Geometry Dash PC")
                        .price(0.99)
                        .console(Console.PC)
                        .reviews(List.of(
                                new Review("Difícil pero adictivo", 5),
                                new Review("Me encanta la música", 2),
                                new Review("Diseño de niveles creativo", 4),
                                new Review("Puede frustrar a principiantes", 4)
                        ))
                        .officialWebsite("https://www.geometrydashgame.com")
                        .isDiscount(false)
                        .totalSold(236)
                        .build(),

                Videogame.builder()
                        .name("Luigi's Mansion")
                        .price(50.06)
                        .console(Console.NINTENDO)
                        .reviews(Collections.emptyList())
                        .officialWebsite("https://www.nintendo.com/luigi")
                        .isDiscount(false)
                        .totalSold(49)
                        .build(),

                Videogame.builder()
                        .name("Mario Party")
                        .price(22.00)
                        .console(Console.NINTENDO)
                        .reviews(List.of(new Review("Divertido con amigos, solo es aburrido", 3)))
                        .officialWebsite("https://www.nintendo.com/mario-party")
                        .isDiscount(false)
                        .totalSold(4)
                        .build(),

                Videogame.builder()
                        .name("Call of Duty: Black Ops")
                        .price(18.60)
                        .console(Console.PLAYSTATION)
                        .reviews(List.of(
                                new Review("Campaña intensa y multijugador sólido", 5),
                                new Review("Zombies es lo mejor", 5)
                        ))
                        .officialWebsite("https://www.callofduty.com/blackops")
                        .isDiscount(false)
                        .totalSold(900)
                        .build(),

                Videogame.builder()
                        .name("Call of Duty: Mobile")
                        .price(0.0)
                        .console(Console.MOBILE)
                        .reviews(List.of(new Review("Buena adaptación móvil", 4)))
                        .officialWebsite("https://www.callofduty.com/mobile")
                        .isDiscount(false)
                        .totalSold(1200)
                        .build(),

                Videogame.builder()
                        .name("Geometry Dash")
                        .price(1.0)
                        .console(Console.MOBILE)
                        .reviews(List.of(
                                new Review("Excelente para jugar en ratos libres", 5),
                                new Review("Te engancha rápido", 4),
                                new Review("Perfecto para desafiar reflejos", 5),
                                new Review("Tiene muchos niveles de la comunidad", 5),
                                new Review("A veces es repetitivo", 4),
                                new Review("Actualizaciones constantes", 5)
                        ))
                        .officialWebsite("https://www.geometrydashgame.com")
                        .isDiscount(true)
                        .totalSold(2000)
                        .build(),

                Videogame.builder()
                        .name("Candy Crush")
                        .price(0.0)
                        .console(Console.MOBILE)
                        .reviews(List.of(
                                new Review("Entretenido pero repetitivo", 4),
                                new Review("Bueno para matar el tiempo", 3),
                                new Review("Demasiados anuncios", 3),
                                new Review("Micropagos constantes", 3),
                                new Review("A veces frustrante", 3),
                                new Review("Aún así, engancha", 4)
                        ))
                        .officialWebsite("https://www.candycrushsaga.com")
                        .isDiscount(false)
                        .totalSold(2200)
                        .build(),

                // Ajuste al registro repetido de "Forza Horizon 5": lo renombramos a Edición Deluxe.
                Videogame.builder()
                        .name("Forza Horizon 5 Edición Deluxe")
                        .price(60.99)
                        .console(Console.XBOX)
                        .reviews(List.of(
                                new Review("Contenido extra muy bueno", 5),
                                new Review("El mejor juego de carreras actual", 5),
                                new Review("Un poco caro para ser DLC", 4)
                        ))
                        .officialWebsite("https://www.forzamotorsport.net/deluxe")
                        .isDiscount(false)
                        .totalSold(90)
                        .build(),

                Videogame.builder()
                        .name("The Legend of Zelda: Breath of the Wild")
                        .price(59.99)
                        .console(Console.NINTENDO)
                        .reviews(List.of(
                                new Review("Obra maestra de Nintendo", 5),
                                new Review("Mundo abierto brillante", 5)
                        ))
                        .officialWebsite("https://www.zelda.com/breath-of-the-wild")
                        .isDiscount(false)
                        .totalSold(120)
                        .build(),

                Videogame.builder()
                        .name("Pokémon Sword")
                        .price(49.99)
                        .console(Console.NINTENDO)
                        .reviews(List.of(
                                new Review("Nuevas mecánicas dinámicas", 4),
                                new Review("Graficos mejorables", 3)
                        ))
                        .officialWebsite("https://www.pokemon.com/sword-shield")
                        .isDiscount(false)
                        .totalSold(85)
                        .build(),

                Videogame.builder()
                        .name("Red Dead Redemption 2")
                        .price(39.99)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("La narrativa es inigualable", 5),
                                new Review("Demasiado realismo en algunos momentos", 4),
                                new Review("Banda sonora espectacular", 5)
                        ))
                        .officialWebsite("https://www.rockstargames.com/reddeadredemption2")
                        .isDiscount(false)
                        .totalSold(150)
                        .build(),

                Videogame.builder()
                        .name("Cyberpunk 2077")
                        .price(44.99)
                        .console(Console.PC)
                        .reviews(List.of(
                                new Review("Historia envolvente, pero con bugs al inicio", 3),
                                new Review("Gráficamente impresionante en PC potente", 5)
                        ))
                        .officialWebsite("https://www.cyberpunk.net")
                        .isDiscount(false)
                        .totalSold(110)
                        .build(),

                Videogame.builder()
                        .name("Fortnite")
                        .price(0.0)
                        .console(Console.MOBILE)
                        .reviews(List.of(
                                new Review("El rey de los Battle Royale", 5),
                                new Review("Demasiados cosméticos de pago", 3)
                        ))
                        .officialWebsite("https://www.epicgames.com/fortnite")
                        .isDiscount(false)
                        .totalSold(3000)
                        .build(),

                Videogame.builder()
                        .name("Elden Ring")
                        .price(59.99)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("Combina el estilo Dark Souls con mundo abierto", 5),
                                new Review("Dificultad alta, pero gratificante", 5)
                        ))
                        .officialWebsite("https://www.eldenring.com")
                        .isDiscount(false)
                        .totalSold(210)
                        .build(),

                Videogame.builder()
                        .name("FIFA 23")
                        .price(49.99)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("Mejora de físicas y animaciones", 4),
                                new Review("Los servidores a veces fallan", 3)
                        ))
                        .officialWebsite("https://www.ea.com/games/fifa")
                        .isDiscount(false)
                        .totalSold(500)
                        .build(),

                Videogame.builder()
                        .name("Minecraft")
                        .price(26.95)
                        .console(Console.ALL)
                        .reviews(List.of(
                                new Review("Creatividad infinita", 5),
                                new Review("Mejor con mods", 4)
                        ))
                        .officialWebsite("https://www.minecraft.net")
                        .isDiscount(false)
                        .totalSold(2380)
                        .build(),

                Videogame.builder()
                        .name("League of Legends")
                        .price(0.0)
                        .console(Console.PC)
                        .reviews(List.of(
                                new Review("Muy competitivo y adictivo", 5),
                                new Review("La comunidad puede ser tóxica", 3)
                        ))
                        .officialWebsite("https://www.leagueoflegends.com")
                        .isDiscount(false)
                        .totalSold(4000)
                        .build(),

                Videogame.builder()
                        .name("Clash Royale")
                        .price(0.0)
                        .console(Console.MOBILE)
                        .reviews(List.of(
                                new Review("Estrategia en tiempo real muy divertida", 5),
                                new Review("Difícil subir de nivel sin pagar", 3)
                        ))
                        .officialWebsite("https://clashroyale.com")
                        .isDiscount(false)
                        .totalSold(1800)
                        .build()
        );

        return Flux.fromIterable(videogames);
    }


    public static Flux<Videogame> fluxAssassinsDefault = Flux.just(
            Videogame.builder()
                    .name("Assassin's Creed Origins (V2)")
                    .price(19.99)
                    .console(Console.ALL)
                    .build(),
            Videogame.builder()
                    .name("Assassin's Creed Odyssey (V2)")
                    .price(19.99)
                    .console(Console.ALL)
                    .build(),
            Videogame.builder()
                    .name("Assassin's Creed Valhalla (V2)")
                    .price(29.99)
                    .console(Console.ALL)
                    .build()
    );


    //DB For fallback :)
    public static Flux<Videogame> fluxFallback = Flux.just(
            Videogame.builder()
                    .name("Fallback data 1")
                    .price(19.99)
                    .console(Console.ALL)
                    .build(),
            Videogame.builder()
                    .name("Fallback data 2")
                    .price(19.99)
                    .console(Console.ALL)
                    .build(),
            Videogame.builder()
                    .name("Fallback data 3")
                    .price(29.99)
                    .console(Console.ALL)
                    .build()
    );

}
