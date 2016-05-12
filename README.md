# Palitinhos_RMI
Jogo dos Palitinhos implementado utilizando RMI + Java

#Configuração do run as

Servidor
-Djava.security.policy=file:${workspace_loc}/Palitinhos_RMI/security.policy -Djava.rmi.server.codebase=file:${workspace_loc}/Palitinhos_RMI/bin/

Cliente
-Djava.security.policy=file:${workspace_loc}/Palitinhos_Cliente/security.policy -Djava.rmi.server.codebase=file:${workspace_loc}/Palitinhos_RMI/bin/
