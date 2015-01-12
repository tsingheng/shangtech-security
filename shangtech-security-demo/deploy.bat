git add --all
git commit -m "deploy"
git fetch
git rebase origin/1.0.0
scp target/shangtech-security-demo-1.0.0.war root@121.41.25.49:usr/local/apache-tomcat/webapps
ssh root@121.41.25.49
cd /usr/local/apache-tomcat/bin
sh shutdown.sh
sh startup.sh
tail ../logs/catalina.out