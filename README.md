# Vap projekt
- repozitář pro vývoj Java projektu na předmět Vap
- Skupina: Vlček, Rehák, Pták, Duchek
- Třída: 3.H
# Definice projektu
- Cíl a název hry (tagy): platformer, pixelart, stationary camera, simple enemies, collectibles
- Téma:
   - Návrhy:
      -  Gargamel hledá sošku (DnD reference) - collectibles: části sošky
      -  Skeleton se snaží dostat na povrch (únik smrti/z podsvětí) - collectibles: kosti/části duše
- Game structure:
  1. Menu
     - (optional) SFX on/off
     - (optional) fullscreen
     - (optional) level select
     - (optional) ingame speedrun timer
  3. Intro (krátký dialog vysvětlující děj)
  4. Level
  5. (optional) lore drop - character development (->3.)
  6. Game over(3 konce (záleží na počtu collectibles))
- Gameplay loop:
   - Cíl: pozbírat collectibles, dostat se do dalšího levelu
   - Combat: pouze se vyhýbat (can change)
   - (optional) Získávání abilit (v průběhu hry/na konci za collectibles)
      - double jump
      - dash
   - Level design/mechanika levelů:
      - odemknutelné dveře
      - moving platformy
      - instant death spikes
      - void
      - (optional) dart trap
   - (optional) Achievementy
- Počet hráčů: 1 (potenciálně více - splitscreen/local multiplayer)
- Ovládání: **klávesnice**, myš(menu - pokud bude, low priority), ovladač(pokud bude moc volného času)
- Grafika: ano (pixelart 16x16)
- Zvuky: jednoduchý soundtrack, SFX

# Technical setup
## Setup
- Vývojové prostředí: [**IntelliJ IDEA Community Edition**](https://www.jetbrains.com/idea/download/?section=windows) (možné změnit, ale zatím funguje dobře, tak uvidíme)
- Verze Java: [**Oracle OpenJDK 17.0.xx**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (preferovaná nejnovější)
## Workflow
**1. Branches**
   - main branch je chráněna (nelze přímo provádět změny)
   - veškeré změny tedy provádějte na vlastních branch
     - formát pojmenování - [username]/main (např.:"wlczak/main") nebo [username]/[featureName] (např.:"wlczak/npcDialog")

**2. Pull request**
  - pro změny v main musíte z vlastní branch vytvořit pull request
  - ten musí být schválen alespoň jedním dalším uživatelem, než se změny provedou
