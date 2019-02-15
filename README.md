# novelsns
小説投稿SNSサイトを目標として、職業訓練内で3日程で作ったものです。
会員登録、ログイン、小説投稿機能までの実装になっています。
現状は、register.jspをホーム画面としています。

# 会員登録
・データベースにユーザーの記入したID、メールアドレス、名前、パスワードを登録する

# ログイン
・ユーザーが入力したユーザーID、パスワードが一致するかを確認し、一致する場合はログイン状態にする

# 小説投稿
・小説タイトル、小説本文、小説内容説明を記入し、データベースに登録する

# 今後の課題
・サイトデザインを決める
・ホーム画面を含む、未実装の画面の実装（投稿した小説を表示させる、投稿した小説の編集）
・データベースに保存するパスワードを、平文ではなくハッシュ化する
・投稿する小説のジャンルを選び、データベースに登録させる
・同じID、メールアドレスがある場合は登録をfalseにする
・IDまたはメールアドレス＋パスワードでログインできるようにする
・投稿された小説を検索する機能の実装
・お気に入り小説を登録する機能の実装
・しおり機能の実装
