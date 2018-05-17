for ARGUMENT in "$@"
do

    KEY=$(echo $ARGUMENT | cut -f1 -d=)
    VALUE=$(echo $ARGUMENT | cut -f2 -d=)   

    case "$KEY" in
            --msg)              msg=${VALUE} ;;
            *)   
    esac    
done

git add .
git commit -m $msg
git pull
git push