# AWS CLI Commands for CloudFormation

## Create CodePipeline Stack(s)

### CodeCommit
```
aws cloudformation validate-template --template-body file://1_CodeCommit.yml 

aws cloudformation create-stack --stack-name gestionFondosRepo --template-body file://1_CodeCommit.yml --parameters file://params/1_CodeCommit-params.json

aws cloudformation describe-stacks --stack-name gestionFondosRepo

aws cloudformation describe-stacks --stack-name gestionFondosRepo --profile gadson | grep StackStatus

aws cloudformation list-stack-resources --stack-name gestionFondosRepo
```

### S3 Artifact Bucket
```
aws cloudformation validate-template --template-body file://2_S3ArtifactBucket.yml 

aws cloudformation create-stack --stack-name gestionFondosBucket --template-body file://2_S3ArtifactBucket.yml --parameters file://params/2_S3ArtifactBucket-params.json 

aws cloudformation describe-stacks --stack-name gestionFondosBucket | grep StackStatus
```

### CodeBuild
```
aws cloudformation validate-template --template-body file://3_CodeBuild.yml

aws cloudformation create-stack --stack-name gestionFondosBuild --template-body file://3_CodeBuild.yml --parameters file://params/3_CodeBuild-params.json --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name gestionFondosBuild | grep StackStatus
```

### CodeDeploy
```
aws cloudformation validate-template --template-body file://4_CodeDeploy.yml

aws cloudformation create-stack --stack-name gestionFondosDeploy --template-body file://4_CodeDeploy.yml --parameters file://params/4_CodeDeploy-params.json --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name gestionFondosDeploy | grep StackStatus
```

### CodePipeline
```
aws cloudformation validate-template --template-body file://5_CodePipeline.yml

aws cloudformation create-stack --stack-name gestionFondosPipeline --template-body file://5_CodePipeline.yml --parameters file://params/5_CodePipeline-params.json  --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name gestionFondosPipeline | grep StackStatus
```


## Delete Stacks
```
aws cloudformation delete-stack --stack-name gestionFondosPipeline

aws cloudformation delete-stack --stack-name gestionFondosDeploy

aws cloudformation delete-stack --stack-name gestionFondosBuild

aws cloudformation delete-stack --stack-name gestionFondosBucket

aws cloudformation delete-stack --stack-name gestionFondosRepo
```
