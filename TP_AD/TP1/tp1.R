tabac = read.table("tabac.txt", header = T, row.names = 1)
#header: une valeur logique (FALSE ou TRUE) indicant si le fichier contient les noms des variables sur la 1ère ligne
tabac
tabac$Vente # permet de réecupérer un vecteur avec toutes les valeurs
tabac$Prix
mean(tabac$Vente)
mean(tabac$Prix)
cor(tabac$Prix, tabac$Vente) #corrélation entre les deux variables
plot(tabac$Prix,tabac$Vente) # tracé simple
plot(tabac$Prix,tabac$Vente, main="Consommation de tabac en France",col="blue",lwd=2,xlab="Prix relatif de vente (en euros)",ylab="Nombre de cigarettes vendues(en milliards)") # tracé plus sophistiqué
reg = lm(Vente~Prix, data = tabac)
# Pour utilser la commande lm, on doit indiquer le nom de la variable
# cible (ici Vente),le signe ~, le nom de la variable explicative (ici Prix)
# et le nom du tableau de donn´ees (ici tabac)
reg
reg$coefficients # a=109.4994 b=-0.1822161
abline(reg, col = "red")
# avant de faire abline assure toi d'avoire la fenêtre du graphique issus de plot ouverte

summary(reg)

reg$residuals # afficher les résidus ei associés au modèle
round(reg$residuals,3)

reg$fitted.values # les valeurs prédites par le modèle ^yi

SCEr = sum(reg$residuals^2)

ybar = mean(tabac$Vente)
SCEt = sum((tabac$Vente - ybar)^2)

SCEm = sum((reg$fitted - ybar)^2)

Rcarre = SCEm / SCEt

summary(reg)$r.squared # obtenir directement le coefficient de détermination R²

summary(reg)$sigma^2 # obtenir l'estimation de la variance résiduelle
n = length(tabac$Prix) 
sigmaChapeauCarre = (SCEr / (n-2) )

summary(reg)$coefficients
# permet d’obtenir une matrice avec les coefficients du modèle en colonne 1, 
# les écarts-types des estimations des coefficients en colonne2,
# les valeurs associées aux tests de signification de Student en colonne 3, 
# et les probabilités critiques de ces tests en colonne 4.

summary(reg)$coefficients[2,3] # récuperer la variable test pour H0


