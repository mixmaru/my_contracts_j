FROM openjdk:11

# ユーザー作成
ARG USERNAME=app_user
ARG GROUPNAME=app_user
ARG UID=1000
ARG GID=1000

RUN groupadd -g $GID $GROUPNAME && \
    useradd -m -s /bin/bash -u $UID -g $GID $USERNAME

USER $USERNAME
WORKDIR /home/$USERNAME/
