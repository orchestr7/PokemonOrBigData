rm -rf posidata.ru
rm -rf PokemonOrBigData
mkdir posidata.ru
git clone https://github.com/orchestr7/PokemonOrBigData.git
cd PokemonOrBigData
git checkout origin/main
cd ../
cp -r PokemonOrBigData/frontend/src/jsMain/resources/* posidata.ru/
cd PokemonOrBigData
git checkout origin/gh-pages
cd ../
cp -r PokemonOrBigData/* posidata.ru/
